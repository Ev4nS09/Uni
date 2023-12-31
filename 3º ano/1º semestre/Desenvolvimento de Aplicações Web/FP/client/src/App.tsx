import {Route, Routes} from "react-router-dom";

import Navbar from "./components/Navbar";
import MainPage from "./components/MainPage";
import Register from "./components/Register";
import Login from "./components/Login";
import CreatePost from "./components/CreatePost";
import DeleteUser from "./components/DeleteUser";

// Main component managing routes and rendering components
function App() {
	return (
    	<div className="bg bg-secondary">
			{/* Navbar component for navigation */}
      		<Navbar />
      		<div>
				{/* Routes component to define different routes and their corresponding components */}
        		<Routes>
					{/* Route for the home page */}
          			<Route path="/" element={<MainPage/>} />
					{/* Route for the register page */}
          			<Route path="/register" element={<Register/>} />
					{/* Route for the login page */}
          			<Route path="/login" element={<Login/>}/>
					{/* Route for creating a new post */}
          			<Route path="/createPost" element={<CreatePost/>}/>
					{/* Route for deleting the user account */}
					<Route path="/deleteUser" element={<DeleteUser/>}/>
        		</Routes>
      		</div>
    	</div>
  	);
}

export default App;
