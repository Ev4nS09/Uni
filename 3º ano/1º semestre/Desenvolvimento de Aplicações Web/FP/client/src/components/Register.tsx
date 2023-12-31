import React, {useEffect, useState} from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { RootState } from '../state/store';

/**
 * Interface representing a temporary user
 */
interface ITempUser
{
    email: string,
    username: string,
    password: string
}

/**
 * Functional React component for user registration
 */
function Register() 
{
    //Gets the user and actions from the store
    const user = useSelector((state: RootState) => state.user);

    // Navigate from one route to another
    const navigate = useNavigate();

    // Checks if the user is logged in, if not returns to the home page
    useEffect(() => {
        if (user._id !== "")
            navigate("/");
    }, []);

    // State variables to manage user inputs
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    // Handle input changes for username, email, password, and confirmPassword fields
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {id, value} = e.target;
        if (id === "username")
            setUsername(value);
        if (id === "email")
            setEmail(value);
        if (id === "password")
            setPassword(value);
        if (id === "confirmPassword")
            setConfirmPassword(value);
    }

    // Clear input fields
    const clearInput = () => {
        setUsername('');
        setEmail('');
        setPassword('');
        setConfirmPassword('');
    }

    // Handle form submission, alerting user and clearing input
    const handleSubmit = async () => {
        if (username === '' || email === '' || password === '' || confirmPassword === '')
            alert('Field(s) cannot be empty.');
        else if (!isValidEmail(email))
            alert('Invalid email.');
        else if (password !== confirmPassword)
            alert('Passwords do not match.');
        else 
        {
            const user: ITempUser = {
                email: email,
                username: username,
                password: password,
            };
            await registerUser(user);
        }
        clearInput();
    }

    // Validate email format using regular expression
    const isValidEmail = (email: string) => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    // Register a user by making a POST request to the server
    const registerUser = async (user: ITempUser) => {
        try 
        {
            const response = await fetch("http://localhost:8080/users/register", {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user)
            });
            if (response.status === 400 || response.status === 500)
                alert(await response.text());
            else if (response.status === 200)
            {
                console.log(await response.json());
                alert("Register successful");
                navigate("/");
            }
        } 
        catch (err) 
        {
            console.error(err);
        }
    }

    // Render the user registration form
    return (
      <div className="form container vh-100 ">
          <div className="form-body row mt-5">
              <div className="col-lg-4 bg-secondary-subtle m-auto rounded">
                <h2 className="text-center pt-3">Register now</h2>
                {/* Input fields for username, email, password, and confirmPassword */}
                  <div className="username input-group mb-3">
                      <input className="form-control" type="text" value={username} onChange={(e) => handleInputChange(e)} id="username" placeholder="Username"/>
                  </div>
                  <div className="email input-group mb-3">
                      <input className="form-control" type="email" value={email} onChange={(e) => handleInputChange(e)} id="email" placeholder="Email"/>
                  </div>
                  <div className="password input-group mb-3">
                      <input className="form-control" value={password} onChange={(e) => handleInputChange(e)} type="password"  id="password" placeholder="Password"/>
                  </div>
                  <div className="confirm-password input-group mb-3">
                      <input className="form-control" value={confirmPassword} onChange={(e) => handleInputChange(e)} type="password" id="confirmPassword" placeholder="Confirm Password"/>
                  </div>
                  {/* Button to submit the registration form */}
                  <div className="d-grid border border-5">
                    <button onClick={() => handleSubmit()} type="submit" className="btn btn-success ">Register</button>
                    </div>
              </div>
          </div>
      </div>      
    )       
}

export default Register;