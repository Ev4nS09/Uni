import { PayloadAction, createSlice } from "@reduxjs/toolkit"

/**
 * Interface that structures the shape of a USER in the STORE
 */
interface IUserState
{
    _id: string,
    email: string, 
    username: string
}

/**
 * Sets the initial state to be an empty user
 */
const initialState: IUserState = {
    _id: "",
    email: "", 
    username: ""
}

//Creates the slice
const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        //Login action
        login: (state, action: PayloadAction<IUserState>) => {
            state._id = action.payload._id;
            state.email = action.payload.email;
            state.username = action.payload.username;
        },

        //Logout action
        logout: (state) => {
            state._id = "";
            state.email = "";
            state.username = "";
        }
    }
});

//Export the actions
export const { login, logout } = userSlice.actions;

//Export userSlice
export default userSlice.reducer;