import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { IPost } from "./../../../server/src/posts"
import { useSelector } from "react-redux";
import { RootState } from "../state/store";

// Functional React component for creating a new post
function CreatePost()
{
    // Navigate from one route to another
    const navigate = useNavigate();

    //Gets user from the store
    const user = useSelector((state: RootState) => state.user);

    // Checks if the user is logged in, if not returns to the home page
    useEffect(() => {
        if (user._id === "")
            navigate("/");
    }, []);

    // State variable to manage the body of the post
    const [body, setBody] = useState('');

    // Handle input change for the body of the post
    const handleInputChange = (e: any) => {
		const {id, value} = e.target;
      	if (id === "body")
        	setBody(value);
    }

	// Handle form submission for creating a new post
    const handleSubmit = async () => {
        if (body === '')
        	alert('Empty body.');
        else
        {
            const date: Date = new Date();
            const post : IPost = { 
                authorID: user._id, 
                author: user.username, 
                content: body, 
                createdAt: date,
                readableCreatedAt: date.toUTCString() 
            };
            await createPost(post);
        }
    };

	// Create a new post by making a POST request to the server
    const createPost = async (post: IPost) => {
        try 
        {
            const response = await fetch("http://localhost:8080/posts", {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(post)
            });
            if (response.status === 500)
            	alert(await response.text());
            else if (response.status === 200)
            {
                console.log(await response.json());
                alert("Post created with success");
                navigate("/");
            }
        } 
        catch (err) 
        {
            console.error(err);
        }
    };

	// Render the form for creating a new post
  	return (
		<div className="container vh-100">
      		<div className="row mt-5">
        		<div className="col-lg-8 m-auto">
          			<div className="bg-secondary rounded p-4">
            		<h1 className="text-center text-white">Create a post</h1>
              			<div className="mb-3">
                		<label htmlFor="commentDescription" className="form-label text-white">Description</label>
						{/* Input fields for post content */}
						<textarea
							id="body"
							className="form-control"
							required
							value={body}
							onChange={(e) => handleInputChange(e)}
							placeholder="Post here"
						>
						</textarea>
						</div>
						{/* Button to submit the create post form */}
						<div className="d-grid gap-2">
							<button onClick={() => handleSubmit()} type="submit" className="btn btn-primary">Submit Post!</button>
						</div>
          			</div>
        		</div>
      		</div>
    	</div>
  	)
}

export default CreatePost;