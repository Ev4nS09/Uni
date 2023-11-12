import express, { Express } from "express";
import router from "./routes";
import compression from "compression";

const app: Express = express();

app.use("/users", router);
app.listen(8080);