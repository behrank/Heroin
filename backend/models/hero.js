const mongoose = require("mongoose");
const collections = require("../db/collectionNames");
const responseCode = require("../utilities/responseCode");
const _dictionary = require("../localization/dictionary");
const Schema = mongoose.Schema;
const objectId = Schema.Types.ObjectId;

const heroSchema = new Schema({
    _id:objectId,
    nickname: String,
    dateCreated: Date,
    avatar: String,
    charisma:Number,
    courage:Number,
    respect:Number,
    latitude:String,
    longitude:String
}, { versionKey: false });

mongoose.model("hero", heroSchema, collections.hero);
var model = mongoose.model("hero");

model.getListByIdArray = (idArray, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        const query = {
            _id: {
                $in: idArray
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

/*
    model.herologin = ...

    Burada süper kahramanlar için yüz tanıma kullanılır. 
    Kahramanların yüzlerinin birden fazla resmi kullanılarak, OpenCV kütüphanesi ile 
    yüz tanıma üzerinden login olması sağlanır. Zaman yetersizliği nedeniyle implementasyon 
    yapılmamıştır. 

    Behran Kankul
*/

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