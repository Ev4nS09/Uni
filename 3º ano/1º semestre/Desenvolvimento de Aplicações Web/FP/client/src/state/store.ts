import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user/userSlice";

//Defines the store, composing of a a userSlice, storing non sensitive data to the user
export const store = configureStore({
    reducer: {
        user: userReducer
    }
});

//Exports the userState type IUserState
export type RootState = ReturnType<typeof store.getState>;

//Exports the user related actions (login, logout)
export type AppDispatch = typeof store.dispatch;
