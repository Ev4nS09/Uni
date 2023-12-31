"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const postsRoutes_1 = __importDefault(require("./postsRoutes"));
const usersRoutes_1 = __importDefault(require("./usersRoutes"));
//Creates new express app
const app = (0, express_1.default)();
//Express app uses JSON
app.use(express_1.default.json());
//Enables Cross-Origin Resource Sharing (CORS)
app.use(function (inRequest, inResponse, inNext) {
    inResponse.header("Access-Control-Allow-Origin", "*");
    inResponse.header("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS, PUT");
    inResponse.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    inNext();
});
//Sets both the posts and users routers up
app.use(postsRoutes_1.default);
app.use(usersRoutes_1.default);
//Server listens for HTTP requests on port 8080 and logs on the console a message saying the same
app.listen(8080, () => console.log("Server listening on port 8080"));
