import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../state/user/userSlice';
import { RootState } from '../state/store';

// Interface representing user credentials
interface ICredentials
{
    email: string,
    password: string
}

// Functional React component for user login
function Login() 
{
    //Gets the user and actions from the store
    const user = useSelector((state: RootState) => state.user);
	const dispatch = useDispatch();

    // Navigate from one route to another
    const navigate = useNavigate();

    // Fetch posts from the API when the component mounts
    useEffect(() => {
        if (user._id !== "")
            navigate("/");
    }, []);


    // State variables to manage user inputs
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    // Handle input changes for email and password fields
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {id, value} = e.target;
        if (id === "email")
            setEmail(value);
        if (id === "password")
            setPassword(value);
    }

    // Clear input fields
    const clearInput = () => {
        setEmail('');
        setPassword('');
    }

    // Handle form submission, alerting user and clearing input
    const handleSubmit = async () => {
        if (email === '' || password === '')
            alert('Field(s) cannot be empty.');
        else if (!isValidEmail(email))
            alert('Invalid email.');
        else 
        {
            const credentials: ICredentials = {
                email: email,
                password: password,
            };
            await loginUser(credentials);
        }
        clearInput();
    }

    // Validate email format using regular expression
    const isValidEmail = (email: string) => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    // Log in a user by making a POST request to the server
    const loginUser = async (credentials: ICredentials) => {
        try 
        {
            const response = await fetch("http://localhost:8080/users/login", {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(credentials)
            });
            if (response.status === 400 || response.status === 500)
                alert(await response.text());
            else if (response.status === 200)
            {
                dispatch(login(await response.json()))
                alert("Login successful");
                navigate("/");
            }
        } 
        catch (err) 
        {
            console.error(err);
        }
    }

    // Render the user login form
    return (
        <div className="form container vh-100 ">
            <div className="form-body row mt-5">
                <div className="col-lg-4 bg-secondary-subtle m-auto rounded">
                <h2 className="text-center pt-3">Login now</h2>
                {/* Input fields for email and password */}
                    <div className="email input-group mb-3">
                        <input className="form-control" type="email" value={email} onChange={(e) => handleInputChange(e)} id="email" placeholder="Email"/>
                    </div>
                    <div className="password input-group mb-3">
                        <input className="form-control" value={password} onChange={(e) => handleInputChange(e)} type="password" id="password" placeholder="Password"/>
                    </div>
                    {/* Button to submit the login form */}
                    <div className="d-grid border border-5">
                        <button onClick={() => handleSubmit()} type="submit" className="btn btn-success ">Login</button>
                    </div>
              </div>
            </div>
        </div>      
    )       
}

export default Login;