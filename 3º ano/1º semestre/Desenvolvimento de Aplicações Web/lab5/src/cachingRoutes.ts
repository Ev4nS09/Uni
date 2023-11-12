import NodeCache from "node-cache";
import { Request, Response, NextFunction } from "express";

const cache = new NodeCache();

interface CustomResponse extends Response{
    originalSend?: any;
}

export default(duration: number) => (req: Request, res: CustomResponse, next: NextFunction) => {
    if(req.method !== "GET"){
        console.log("Cannot cache non-GET methods");
        return next;
    }
    const key = req.originalUrl;
    const cachedResponse = cache.get(key);

    if(cachedResponse){
        console.error(`Cache hit fo ${key}`);
        res.send(cachedResponse);
    }

    else{
        res.originalSend = res.send;
        res.send = body => {
            res.originalSend(body);
            cache.set(key, body, duration);
            return res;
        };
        next();
    }
};