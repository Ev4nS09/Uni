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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Worker = void 0;
const path = __importStar(require("path"));
const nedb_1 = __importDefault(require("nedb"));
class Worker {
    /**
     * Initializes the DB, automatically loading the data from the file users.db
     */
    constructor() {
        this.db = new nedb_1.default({
            filename: path.join(__dirname, "users.db"), autoload: true
        });
    }
    /**
     * Queries the DB for a user with a given email
     * @param inEmail Email
     * @returns Promise that resolves to a IUser
     */
    getUser(inEmail) {
        return new Promise((inResolve, inReject) => {
            this.db.findOne({ email: inEmail }, (inError, inDocs) => {
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
    addUser(inUser) {
        return new Promise((inResolve, inReject) => {
            this.db.insert(inUser, (inError, inNewDoc) => {
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
    deleteUser(inID) {
        return new Promise((inResolve, inReject) => {
            this.db.remove({ _id: inID }, {}, (inError, inNumRemoved) => {
                if (inError)
                    inReject(inError);
                else
                    inResolve();
            });
        });
    }
}
exports.Worker = Worker;
