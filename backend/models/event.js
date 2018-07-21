const mongoose = require("mongoose");
const collections = require("../db/collectionNames");
const responseCode = require("../utilities/responseCode");
const _dictionary = require("../localization/dictionary");
const Schema = mongoose.Schema;
const objectId = Schema.Types.ObjectId;

const eventSchema = new Schema({
    _id:Schema.Types.ObjectId,
    location: {
        type: { type: String },
        coordinates: []
       },
    dateCreated: Date,
    priority:Number,
    status:Number,
    hero: [{type:Schema.Types.ObjectId,ref:"Hero"}],
    user: [{type:Schema.Types.ObjectId,ref:"User"}]
}, { versionKey: false });

mongoose.model("event", eventSchema, collections.event);
var model = mongoose.model("event");
var distance = 1000 / 6371;

//Add
model.setNew = (event,req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        if (event.lat == null || event.lng == "") {
            rej({
                message: dictionary.errorMessages.systemError,
                statusCode: responseCode.SERVER_ERROR
            });
        } else if (event.lng == null || event.lat == "") {
            rej({
                message: dictionary.errorMessages.systemError,
                statusCode: responseCode.SERVER_ERROR
            });
        }
        model.create(event)
        .then(res)
        .catch((err) => {
            rej({
                message: dictionary.errorMessages.systemError,
                statusCode: responseCode.SERVER_ERROR
            });
        });
    });
}

//List nearby
model.getListByLocation = (lat,lng, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        const query = {
            $near: {
                $maxDistance: 1000,
                $geometry: {
                 type: "Point",
                 coordinates: [lat, lng]
                }
            }
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
//Single item
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