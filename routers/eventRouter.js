const express = require("express");
const bodyParser = require("body-parser");
const guid = require("guid");
const math = require("../utilities/math");
const eventModel = require("../models/event");
const userModel = require("../models/user")
const responseCode = require("../utilities/responseCode");
const redisClient = require("../db/redis");
const voteModel = require("../models/vote")
const redisKeys = require("../db/redisKeys");
const response = require("../utilities/response");
const _dictionary = require("../localization/dictionary");
let router = express.Router();

router.use(bodyParser.urlencoded({ extended: true }));
router.use(bodyParser.json());

router.post("/", (req, res) => {
    console.log(date);
    var evnt = new Event({
        priority: req.body.priority,
        user: req.body.userId,
        location: {
         type: "Point",
         coordinates: [req.body.lat,req.body.lng]
        },
        dateCreated : Date.now()
    });
    eventModel.setNew(evnt,req)
            .then((doc) => {                
                res.status(responseCode.CREATED)
                    .send(response(responseCode.CREATED, "", {
                        key: "event",
                        value: doc
                    }));
            })
            .catch((err) => {
                res.status(err.statusCode)
                    .send(response(err.statusCode, err.message));
            });
});
router.get("/",(req,res) => {
    eventModel.getListByLocation(req.body.lat,req.body.lng,req)
    .then((doc) => {
        res.status(responseCode.OK)
            .send(response(responseCode.OK, "", {
                key: "events",
                value: doc
            }));
    })
    .catch((err) => {
        res.status(err.statusCode)
            .send(response(err.statusCode, err.message));
    });
});
router.post("/vote",(req,res) =>{
    var vote = new Vote({
        charisma:req.body.charPoint,
        courage:req.body.couragePoint,
        respect:req.body.resPoint,
        eventId:req.body.eventId,
        hero: req.body.heroId,
        user: req.body.userId
    });
    vote.save((err, vote) => {
        if (err) console.log(err);
        console.log(vote);
        res.status(responseCode.CREATED)
        .send(response(responseCode.CREATED, "", {
            key: "vote",
            value: vote
        }));
    });
});
module.exports = router;