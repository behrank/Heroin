const express = require("express");
const db = require("./db");
const logger = require("./utilities/logger");
const consoleLogger = logger.init(logger.types.CONSOLE);
const config = require("./utilities/config");
const responseCode=require("./utilities/responseCode");
const response=require("./utilities/response");

const app = express()

app.get('/', (req, res) => res.send('Hello World!'))

app.listen(process.env.PORT || 3000, () => console.log('Example app listening on port 3000!'))