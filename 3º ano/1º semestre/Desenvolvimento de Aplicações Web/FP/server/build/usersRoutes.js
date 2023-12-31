"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const Users = __importStar(require("./users"));
const bcrypt_1 = __importDefault(require("bcrypt"));
//Creates new express Router
const usersRouter = express_1.default.Router();
//Creates both posts and users workers
const usersWorker = new Users.Worker();
//Sets all endpoint routes related to posts
//Makes it so when a GET request is recieved by the server on /register it validates the user credentials on the DB and returns the same, including it's ID
//Or an empty object in case it failed logging in
usersRouter.post("/users/register", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const user = yield usersWorker.getUser(req.body.email); //Queries the db to see if email is already taken
        if (user)
            res.status(400).send("This email is already registered");
        else {
            let newUser = {
                email: req.body.email,
                username: req.body.username,
                passwordHash: yield bcrypt_1.default.hash(req.body.password, 10) //Hashes the password
            };
            newUser = yield usersWorker.addUser(newUser);
            res.status(200).json({ _id: newUser._id, email: newUser.email, username: newUser.username }); //Returns not sensitive information about the user
        }
    }
    catch (err) {
        res.status(500).send("Ocurred error while registering to DB");
    }
}));
//Makes it so when a POST request is recieved by the server on /login it validates the user credentials on the DB and returns the same, including it's ID
//Or an empty object in case it failed logging in
usersRouter.post("/users/login", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const user = yield usersWorker.getUser(req.body.email); //Queries the db to see if email is available
        if (!user)
            res.status(400).send("This email is not registered");
        else if (!(yield bcrypt_1.default.compare(req.body.password, user.passwordHash))) //Validates password
            res.status(400).send("Wrong password");
        else
            res.status(200).json({ _id: user._id, email: user.email, username: user.username }); //Returns not sensitive information about the user
    }
    catch (err) {
        res.status(500).send("Ocurred error while logging in");
    }
}));
//Makes it so when a DELETE request is recieved by the server on /users/:id to delete the user with the given id given on the URL
usersRouter.delete("/users/:id", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        yield usersWorker.deleteUser(req.params.id);
        res.status(200).send("User deleted with success");
    }
    catch (err) {
        res.status(500).send("Ocurred error while deleting user from DB");
    }
}));
exports.default = usersRouter;
