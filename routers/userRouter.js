const express = require("express");
const bodyParser = require("body-parser");
const guid = require("guid");
const linq = require("ex-js-linq");
const math = require("../utilities/math");
const courierModel = require("../models/courier");
const bookModel = require("../models/book");
const userModel = require("../models/user");
const notificationTokenModel = require("../models/notificationToken");
const locationModel = require("../models/location");
const tokenModel = require("../models/token");
const objectIniter = require("../helpers/objectIniter");
const responseCode = require("../utilities/responseCode");
const redisClient = require("../db/redis");
const redisKeys = require("../db/redisKeys");
const response = require("../utilities/response");
const _dictionary = require("../localization/dictionary");
let router = express.Router();

router.use(bodyParser.urlencoded({ extended: true }));
router.use(bodyParser.json());

router.post("/login", (req, res) => {
    var dictionary = _dictionary(req);
    userModel.login(req.body.email, req.body.password,req)
        .then((user) => {
            var token = guid.create();
            return tokenModel.create({
                userId: user._id,
                token: token,
                dateCreated: Date.now(),
                isActive: true
            }).then((token) => {
                return {
                    token: token,
                    user: user
                }
            });
        })
        .then((result) => {
            redisClient.set(redisKeys.userToken(res.user._id.toString()), result.token.token);
            res.status(responseCode.OK)
                .send(response(responseCode.OK, "", {
                    key: "user",
                    value: {
                        user: result.user,
                        token: result.token.token
                    }
                }));
        }).catch((err) => {
            res.status(responseCode.SERVER_ERROR)
                .send(response(err.statusCode,err.message));
        })
});

router.get("/:userId/logout", (req, res) => {
    var dictionary = _dictionary(req);
    var token = req.headers["authorization"];
    tokenModel.deleteByUserIdAndToken(req.params.userId, token,req)
        .then((r) => {
            redisClient.del(redisKeys.userToken(req.params.userId));
            res.status(responseCode.OK)
                .send(response(responseCode.OK, ""));
        })
        .catch((err) => {
            res.status(responseCode.SERVER_ERROR)
                .send(response(responseCode.SERVER_ERROR, dictionary.errorMessages.systemError));
        });

});

router.post("/:userId/notification/token", (req, res) => {
    notificationTokenModel.insert(req.params.userId, req.body.token, req)
        .then((token) => {
            redisClient.lpush(redisKeys.userPushNotificationToken(req.params.userId), token.token);
            res.status(responseCode.OK)
                .send(response(responseCode.OK, "", {
                    key: "token",
                    value: token
                }));
        })
        .catch((err) => {
            res.status(err.statusCode)
                .send(response(err.statusCode, err.message));
        });
});

router.post("/", (req, res) => {
    var dictionary = _dictionary(req);
    var user = {
        dateCreated: Date.now(),
        name: req.body.name,
        surname: req.body.surname,
        email: req.body.email,
        password: req.body.password,
    };
    const query = {
        email: user.email
    };
    const projection = {
        _id: 1
    };
    userModel.findOne(query, projection)
        .then((doc) => {
            if (doc == null) {
                userModel.create(user)
                    .then((doc) => {
                        res.status(responseCode.OK)
                            .send(response(responseCode.OK, "", {
                                key: "user",
                                value: {
                                    _id: doc._id,
                                    name: doc.name,
                                    surname: doc.surname,
                                    email: doc.email
                                }
                            }));
                    })
                    .catch((err) => {
                        res.status(responseCode.SERVER_ERROR)
                            .send(response(responseCode.SERVER_ERROR, err));
                    });
            } else {
                res.status(responseCode.OK)
                    .send(response(responseCode.OK, dictionary.errorMessages.usedToEmail));
            }
        })
        .catch((err) => {
            res.status(responseCode.SERVER_ERROR)
                .send(response(responseCode.SERVER_ERROR, dictionary.errorMessages.systemError));
        });

});



module.exports = router;