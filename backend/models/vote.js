const mongoose = require("mongoose");
const collections = require("../db/collectionNames");
const responseCode = require("../utilities/responseCode");
const _dictionary = require("../localization/dictionary");
const Schema = mongoose.Schema;
const objectId = Schema.Types.ObjectId;

const voteSchema = new Schema({
    _id:objectId,
    charisma:Number,
    courage:Number,
    respect:Number,
    eventId:objectId,
    hero: objectId,
    user: objectId
}, { versionKey: false });

mongoose.model("hero", voteSchema, collections.vote);
var model = mongoose.model("hero");

model.getListByHeroId = (id, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        const query = {
            hero:id
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


module.exports = model;