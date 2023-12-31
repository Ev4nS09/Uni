import express, { Router, Request, Response } from "express";

import * as Posts from "./posts";
import { IPost } from "./posts";

//Creates new express Router
const postsRouter: Router = express.Router();

//Creates both posts and users workers
const postsWorker: Posts.Worker = new Posts.Worker();

//Sets all endpoint routes related to posts

//Makes it so when a get request is recieved by the server on /posts it returns a list of all posts sorted by creation date
postsRouter.get("/posts", async (req: Request, res: Response) => {
    try
    {
        const posts: IPost[] = await postsWorker.listPosts();
        posts.sort((post1: IPost, post2: IPost) => {
            if (post1.createdAt === post2.createdAt)
                return 0;
            else
                return post1.createdAt < post2.createdAt ? 1 : -1
        });
        res.status(200).json(posts);
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while getting posts from DB");
    }
});

//Makes it so when a POST request is recieved by the server on /posts it adds a posts on the DB and returns the same, including it's ID
postsRouter.post("/posts", async (req: Request, res: Response) => {
    try
    {
        const post: IPost = await postsWorker.addPost(req.body);
        res.status(200).json(post);
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while adding post to DB");
    }
});

//Makes it so when a DELETE request is recieved by the server on /posts/:id to delete the post with the given id given on the URL
postsRouter.delete("/posts/:id", async (req: Request, res: Response) => {
    try
    {
        await postsWorker.deletePost(req.params.id);
        res.status(200).send("Post deleted with success");
    }
    catch (err)
    {
        res.status(500).send("Ocurred error while deleting post from DB");
    }  
});

export default postsRouter;