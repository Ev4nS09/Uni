import * as path from "path";
import Datastore from "nedb";

/**
 * Interface that structures the shape of a User in the DB
 */
export interface IUser
{
    _id?: string,
    email: string,
    username: string,
    passwordHash: string 
}

export class Worker 
{ 
    private db: Nedb;

    /**
     * Initializes the DB, automatically loading the data from the file users.db
     */
    constructor() 
    {
        this.db = new Datastore({
            filename : path.join(__dirname , "users.db"), autoload : true
        });
    }

    /**
     * Queries the DB for a user with a given email
     * @param inEmail Email
     * @returns Promise that resolves to a IUser 
     */
    public getUser(inEmail: string): Promise<IUser>
    {
        return new Promise((inResolve , inReject) => { this.db.findOne({ email : inEmail }, (inError: Error | null, inDocs: IUser) => { 
            if (inError)
                inReject(inError); 
            else
                inResolve(inDocs); 
            });
        }); 
    }

    /**
     * Adds a user in the DB
     * @param inUser User
     * @returns Promise that resolves to a IUser 
     */
    public addUser(inUser: IUser): Promise<IUser> 
    {
        return new Promise((inResolve , inReject) => { this.db.insert(inUser, (inError: Error | null, inNewDoc: IUser) => { 
            if (inError)
                inReject(inError); 
            else
                inResolve(inNewDoc); 
            });
        }); 
    }

    /**
     * Adds a user in the DB
     * @param inUser User
     * @returns Promise that resolves to a IUser 
     */  
    public deleteUser(inID: string): Promise<void> 
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