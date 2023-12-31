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
const Posts = __importStar(require("./posts"));
//Creates new express Router
const postsRouter = express_1.default.Router();
//Creates both posts and users workers
const postsWorker = new Posts.Worker();
//Sets all endpoint routes related to posts
//Makes it so when a get request is recieved by the server on /posts it returns a list of all posts sorted by creation date
postsRouter.get("/posts", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const posts = yield postsWorker.listPosts();
        posts.sort((post1, post2) => {
            if (post1.createdAt === post2.createdAt)
                return 0;
            else
                return post1.createdAt < post2.createdAt ? 1 : -1;
        });
        res.status(200).json(posts);
    }
    catch (err) {
        res.status(500).send("Ocurred error while getting posts from DB");
    }
}));
//Makes it so when a POST request is recieved by the server on /posts it adds a posts on the DB and returns the same, including it's ID
postsRouter.post("/posts", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const post = yield postsWorker.addPost(req.body);
        res.status(200).json(post);
    }
    catch (err) {
        res.status(500).send("Ocurred error while adding post to DB");
    }
}));
//Makes it so when a DELETE request is recieved by the server on /posts/:id to delete the post with the given id given on the URL
postsRouter.delete("/posts/:id", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        yield postsWorker.deletePost(req.params.id);
        res.status(200).send("Post deleted with success");
    }
    catch (err) {
        res.status(500).send("Ocurred error while deleting post from DB");
    }
}));
exports.default = postsRouter;
