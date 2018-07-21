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
    event: [{type:Schema.Types.ObjectId,ref:"Event"}],
    hero: [{type:Schema.Types.ObjectId,ref:"Hero"}],
    user: [{type:Schema.Types.ObjectId,ref:"User"}]
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