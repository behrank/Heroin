const mongoose = require("mongoose");
const collections = require("../db/collectionNames");
const responseCode = require("../utilities/responseCode");
const _dictionary = require("../localization/dictionary");
const Schema = mongoose.Schema;
const objectId = Schema.Types.ObjectId;

const eventSchema = new Schema({
    _id:Schema.Types.ObjectId,
    title: String,
    description: String,
    geo: {
        type: [Number],
        index: '2d'
      },
    dateCreated: Date,
    user: [{type:Schema.Types.ObjectId,ref:"User"}]
}, { versionKey: false });

mongoose.model("event", userSchema, collections.users);
var model = mongoose.model("event");
var distance = 1000 / 6371;

model.getListByLocation = (lat,lng, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        const query = {
            $near: [
                req.body.lat,
                req.body.lng
              ],
              $maxDistance: distance
        };
        model.find(query)
            .then(res)
            .catch((err) => {
                rej({
                    message: dictionary.errorMessages.systemError,
                    statusCode: responseCode.BAD_REQUEST
                });
            });
    });
}

model.getById = (id, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        model.findById(id)
            .exec()
            .then(res)
            .catch((err) => {
                rej({
                    message: dictionary.errorMessages.systemError,
                    statusCode: responseCode.SERVER_ERROR
                });
            });
    });
}

module.exports = model;