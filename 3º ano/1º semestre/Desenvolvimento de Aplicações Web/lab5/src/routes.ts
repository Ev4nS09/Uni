declare const fetch : any;
import { getAllUsers, getSomeUsers, transformData } from "./externalAPI";
import express, { Router, Request, Response, NextFunction } from "express";

const router: Router = express.Router();

router.get("/", async (req: Request, res: Response, next: NextFunction) => {
    try
    {
        const data = await getAllUsers();

        const arr = Object.entries(data).map(transformData);

        console.log(arr);
        res.json(arr);
    }
    catch (err)
    {
        next(err);
    }
});

router.get("/city/:nameCity", async (req: Request, res: Response, next: NextFunction) => {
    try
    {
        const data = await getSomeUsers(req.params.nameCity);

        const arr = Object.entries(data).map(transformData);

        res.json(arr);
    }
    catch (err)
    {
        next(err);
    }
});


export default router;