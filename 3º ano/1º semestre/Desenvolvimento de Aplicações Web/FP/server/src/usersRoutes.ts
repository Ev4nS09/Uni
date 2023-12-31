import express, { Router, Request, Response } from "express";

import * as Users from "./users";
import { IUser } from "./users";
import bcrypt from "bcrypt";

//Creates new express Router
const usersRouter: Router = express.Router();

//Creates both posts and users workers
const usersWorker: Users.Worker = new Users.Worker();

//Sets all endpoint routes related to posts

//Makes it so when a GET request is recieved by the server on /register it validates the user credentials on the DB and returns the same, including it's ID
//Or an empty object in case it failed logging in
usersRouter.post("/users/register", async (req: Request, res: Response) => {
    try
    {
        const user: IUser = await usersWorker.getUser(req.body.email); //Queries the db to see if email is already taken
        if (user)
            res.status(400).send("This email is already registered");
        else
        {
            let newUser: IUser = { 
                email: req.body.email, 
                username: req.body.username,
                passwordHash: await bcrypt.hash(req.body.password, 10) //Hashes the password
            };
            newUser = await usersWorker.addUser(newUser);
            res.status(200).json({_id: newUser._id, email: newUser.email, username: newUser.username}); //Returns not sensitive information about the user
        }
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while registering to DB");
    }  
});

//Makes it so when a POST request is recieved by the server on /login it validates the user credentials on the DB and returns the same, including it's ID
//Or an empty object in case it failed logging in
usersRouter.post("/users/login", async (req: Request, res: Response) => {
    try
    {
        const user: IUser = await usersWorker.getUser(req.body.email); //Queries the db to see if email is available
        if (!user)
            res.status(400).send("This email is not registered");
        else if (!await bcrypt.compare(req.body.password, user.passwordHash)) //Validates password
            res.status(400).send("Wrong password");
        else
            res.status(200).json({_id: user._id, email: user.email, username: user.username}); //Returns not sensitive information about the user
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while logging in");
    }  
});

//Makes it so when a DELETE request is recieved by the server on /users/:id to delete the user with the given id given on the URL
usersRouter.delete("/users/:id", async (req: Request, res: Response) => {
    try
    {
        await usersWorker.deleteUser(req.params.id);
        res.status(200).send("User deleted with success");
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while deleting user from DB");
    }  
});

export default usersRouter;