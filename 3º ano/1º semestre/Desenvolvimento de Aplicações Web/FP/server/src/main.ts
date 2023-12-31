import express , {Express, NextFunction, Request, Response} from "express";
import postsRouter from "./postsRoutes";
import usersRouter from "./usersRoutes";

//Creates new express app
const app: Express = express();

//Express app uses JSON
app.use(express.json());

//Enables Cross-Origin Resource Sharing (CORS)
app.use(function(inRequest: Request, inResponse: Response, inNext : NextFunction ) {
    inResponse.header("Access-Control-Allow-Origin", "*");
    inResponse.header("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS, PUT");
    inResponse.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    inNext();
});

//Sets both the posts and users routers up
app.use(postsRouter);
app.use(usersRouter);

//Server listens for HTTP requests on port 8080 and logs on the console a message saying the same
app.listen(8080, () => console.log("Server listening on port 8080"))