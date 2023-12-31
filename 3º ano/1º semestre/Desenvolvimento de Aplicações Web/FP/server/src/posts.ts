import * as path from "path";
import Datastore from "nedb";

/**
 * Interface that structures the shape of a Post in the DB
 */
export interface IPost 
{
    _id?: string,
    authorID: string,
    author: string,
    content: string,
    createdAt: Date,
    readableCreatedAt: string
}

export class Worker 
{ 
    private db: Nedb;

    /**
     * Initializes the DB, automatically loading the data from the file posts.db
     */
    constructor() 
    {
        this.db = new Datastore({
            filename : path.join(__dirname , "posts.db"), autoload : true
        });
    }

    /**
     * Gets all the posts stored in the DB
     * @returns Promise that resolves to an array of Posts
     */
    public listPosts(): Promise<IPost[]> 
    {
        return new Promise((inResolve , inReject) => { this.db.find({}, (inError: Error | null, inDocs: IPost[]) => { 
            if (inError)
                inReject(inError); 
            else
                inResolve(inDocs); 
            });
        }); 
    }

    /**
     * Adds post to DB
     * @param inPost Post to be added
     * @returns Promise that resolves to a Post
     */
    public addPost(inPost: IPost): Promise<IPost> 
    {
        return new Promise((inResolve , inReject) => { this.db.insert(inPost , (inError: Error | null, inNewDoc: IPost) => { 
            if (inError)
                inReject(inError); 
            else
                inResolve(inNewDoc); 
            });
        }); 
    }

    /**
     * Deletes post from DB
     * @param inID Post id to be deleted
     * @returns Promise that resolves to void
     */
    public deletePost(inID: string): Promise<void> 
    {
        return new Promise((inResolve , inReject) => { this.db.remove({ _id : inID }, { }, (inError: Error | null, inNumRemoved: number) => { 
            if (inError)
                inReject(inError); 
            else
                inResolve();
            });
        }); 
    }
}