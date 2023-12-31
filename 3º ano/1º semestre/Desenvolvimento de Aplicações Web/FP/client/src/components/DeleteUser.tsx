import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { RootState } from '../state/store'
import { logout } from '../state/user/userSlice'

/**
 * Interface representing a temporary user
 */
interface ICredentials
{
    _id : string,
    email: string,
    username: string,
}

const DeleteUser = () => {

    const user = useSelector((state: RootState) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    // Checks if the user is logged in, if not returns to the home page
    useEffect(() => {
        if (user._id === "")
            navigate("/");
    }, []);

    const handleSubmit = async () => {
        const user2 : ICredentials = {
            _id: user._id,
            email: user.email,
            username: user.username,
        };
        await deleteUser(user2);
    }

    const deleteUser = async (user: ICredentials) => {
        try {
            const response = await fetch("http://localhost:8080/users/" + user._id, {
                method: 'DELETE',
                headers: { "Content-Type": "application/json" },
            });
            if (response.status === 500)
                alert(await response.text());
            else if (response.status === 200) {
                console.log(await response.json);
                alert("User deleted with success");
                dispatch(logout());
                navigate("/");
            }
        }
        catch (err) {
            console.error(err);
        }
    }

    const cancel = () => {
        alert('Going back.');
        navigate("/");
    }

    return (
        <div className="container mt-5 vh-100">
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card">
                        <div className="card-body text-center bg-secondary-subtle">
                            <h5 className="card-title">Are you sure you want to delete your account?</h5>
                            <p className="card-text">This action cannot be undone.</p>
                            <button onClick={() => cancel()} className="btn btn-secondary me-2">Go Back</button>
                            <button onClick={() => handleSubmit()} className="btn btn-danger">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DeleteUser;