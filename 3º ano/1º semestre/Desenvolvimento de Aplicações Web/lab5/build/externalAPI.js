"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.transformData = exports.getSomeUsers = exports.getAllUsers = void 0;
const MAIN_API_URL = "https://jsonplaceholder.typicode.com/users";
function getAllUsers() {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const response = yield fetch(MAIN_API_URL);
            const data = yield response.json();
            return data;
        }
        catch (error) {
            return "Error inside: getAllUsers()";
        }
    });
}
exports.getAllUsers = getAllUsers;
function getSomeUsers(city) {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const response = yield fetch(MAIN_API_URL + "/?address.city=" + city);
            const data = yield response.json();
            return data;
        }
        catch (error) {
            return "Error inside: getSomeUsers()";
        }
    });
}
exports.getSomeUsers = getSomeUsers;
function changeEmailDomain(email) {
    const splitEmail = email.split("@");
    return splitEmail[0] + "@ualg.pt";
}
function transformData(element) {
    const myElement = element[1];
    return {
        id: myElement.id,
        name: myElement.name,
        login: myElement.username,
        email: changeEmailDomain(myElement.email),
        address: myElement.address,
        phone: myElement.phone,
        website: myElement.website,
        //company: myElement.company -> This one was removed
    };
}
exports.transformData = transformData;
