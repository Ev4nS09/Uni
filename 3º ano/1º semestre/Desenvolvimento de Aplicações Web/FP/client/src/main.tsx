import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import { Provider } from "react-redux";
import { store } from "./state/store";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  //Allows the app to access the redux store
  <Provider store={store}>
    <React.StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </React.StrictMode>
  </Provider>
);
