const redis = require("redis");
var bluebird = require("bluebird");
const appConfig = require("../utilities/config");
const logger = require("../utilities/logger");

bluebird.promisifyAll(redis.RedisClient.prototype);
bluebird.promisifyAll(redis.Multi.prototype);

if (appConfig.cacheServer.connectionString != "") {
    const redisClient = redis.createClient(appConfig.cacheServer.connectionString);
    module.exports = redisClient;
}
else{
    const consoleLogger = logger.init(logger.types.CONSOLE);
    consoleLogger.error("Redis server url undefiend");
}
