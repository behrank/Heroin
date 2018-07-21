const express = require("express");
const db = require("./db");
const logger = require("./utilities/logger");
const consoleLogger = logger.init(logger.types.CONSOLE);
const config = require("./utilities/config");
const responseCode=require("./utilities/responseCode");
const response=require("./utilities/response");

const userRouter=require("./routers/userRouter");
const eventRouter=require("./routers/eventRouter");
//const heroRouter=require("./routers/heroRouter");
const app = express()

app.get('/', (req, res) => res.send('Welcome to Heroin!'))
app.use("/user", userRouter);
app.use("/event",eventRouter);
//app.use("/hero",heroRouter);

app.listen(process.env.PORT || 3000, () => console.log('Heroin works!'))