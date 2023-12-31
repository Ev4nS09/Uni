import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { RootState } from '../state/store';
import { useNavigate } from 'react-router-dom';
import { logout } from '../state/user/userSlice';

// Functional React component for navigation links
function Navbar() 
{
	//Gets the user and actions from the store
    const user = useSelector((state: RootState) => state.user);
	const dispatch = useDispatch();
	const navigate = useNavigate();

	const handleLogout = () => {
		dispatch(logout())
		alert('Logout successful.');
		navigate("/");
	}

	if (user._id === "") //If user isn't logged in show login and register links
		return (
			<nav className="navbar bg-dark navbar-dark">
				<div className="navbar__left">
					{/* Link to the home page */}
					<span style={{ marginLeft: '10px' }}>
						<Link to="/" className="navbar-brand">XUAlg</Link>
					</span>
				</div>
				<div className="navbar__right">
					{/* Link to login page */}
					<Link to="/login" className="navbar-brand">Login</Link>
					{/* Link to register page */}
					<Link to="/register" className="navbar-brand">Register</Link>
				</div>
			</nav>
		);
	else //Otherwise...
		return (
			<nav className="navbar bg-dark navbar-dark">
				<div className="navbar__left">
					{/* Link to the home page */}
					<span style={{ marginLeft: '10px' }}>
						<Link to="/" className="navbar-brand">XUAlg</Link>
					</span>
				</div>
				<div className="navbar__right d-flex align-items-center">
					{/* Links to create a new post, delete account, and logout */}
					<div className="d-flex align-items-center">
						<Link to="/createPost" className="navbar-brand me-2">Create Post</Link>
						<Link to="/deleteUser" className="navbar-brand me-2">Delete Account</Link>
						{/* <Link to="/logout" className="navbar-brand me-2">Logout</Link> */}
						<button onClick={() => handleLogout()} className="navbar-brand me-2" 
						style={{color: 'white',
								backgroundColor: 'transparent',
								border: 'none',
								cursor: 'pointer',
								textDecoration: 'none',
								fontSize: '20px',
								}}
								>
							Logout</button>
					</div>
					{/* Welcome message */}
					<p className="text-white m-0 navbar-brand ">Welcome, {user.username}!</p>
        		</div>
			</nav>
		);
}

export default Navbar;
