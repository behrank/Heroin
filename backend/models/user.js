const mongoose = require("mongoose");
const collections = require("../db/collectionNames");
const responseCode = require("../utilities/responseCode");
const _dictionary = require("../localization/dictionary");
const Schema = mongoose.Schema;
const objectId = Schema.Types.ObjectId;

const userSchema = new Schema({
    _id:objectId,
    name: String,
    surname: String,
    email: String,
    password: String,
    dateCreated: Date,
    avatar: String,
}, { versionKey: false });

mongoose.model("user", userSchema, collections.users);
var model = mongoose.model("user");

model.login = (email, password, req) => {
    var dictionary = _dictionary(req);
    return new Promise((res, rej) => {
        if (email == null || email == "") {
            rej({
                message: dictionary.errorMessages.systemError,
                statusCode: responseCode.SERVER_ERROR
            });
        } else if (password == null || password == "") {
            rej({
                message: dictionary.errorMessages.systemError,
                statusCode: responseCode.SERVER_ERROR
            });
        }
        const query = {
            email: email,
            password: password
        };
        const options = {
            new: true,
            fields: {
                password: 0
            }
        }
        model.findOne(query, options)
            .exec()
            .then(res)
            .catch((err) => {
                rej({
                    message: dictionary.errorMessages.systemError,
                    statusCode: responseCode.SERVER_ERROR
                });
            });
    });
};
module.exports = model;