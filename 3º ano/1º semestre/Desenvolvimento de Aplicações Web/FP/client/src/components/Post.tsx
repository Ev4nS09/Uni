import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { IPost } from "./../../../server/src/posts";
import { RootState } from "../state/store";
import { useNavigate } from "react-router-dom";

/**
 * Fetches all posts from the API
 * @returns Promise that resolves to all posts fetched from the API
 */
async function getPosts()
{
    const response = await fetch("http://localhost:8080/posts");
    const posts = await response.json();
    console.log(posts);
    return posts;
}

function Post() {
    // State to manage the fetched posts
    const [posts, setPosts] = useState([]);
    const user = useSelector((state: RootState) => state.user);
    const navigate = useNavigate();
    
    // Fetch posts from the API when the component mounts
    useEffect(() => {
        getPosts()
        .then((posts) => setPosts(posts))
        .catch((err) => console.error(err))
    }, []);

    const handleSubmit = async (postID : string | undefined) => {
        if (postID)
            await deletePost(postID);
    }

    const deletePost = async (postID: string) => {
        try {
            const response = await fetch("http://localhost:8080/posts/" + postID, {
                method: 'DELETE',
                headers: { "Content-Type": "application/json" },
            });
            if (response.status === 500)
                alert(await response.text());
            else if (response.status === 200) {
                console.log(await response.json);
                alert("Post deleted with success.");
                navigate(0);
            }
        }
        catch (err) {
            console.log(err);
        }
    }

    return (
        <div className="container vh-100">
            {posts.map((post: IPost) => (
                <div key={post._id} className="col-md-12 border border-black bg-secondary" style={{ overflow: 'hidden' }}>
                    <br />
                    <p className="border border-dark-subtle rounded m-0">{post.content}</p>
                    <small>by {post.author}. post created on {post.readableCreatedAt}.</small>
                    {/* Check if the logged-in user is the owner of the post */}
                    { user._id === post.authorID && (
                        <button
                            onClick={() => handleSubmit(post._id)} // Defines a function to handle post deletion
                            className="btn btn-danger"
                        >
                            Delete Post
                        </button>
                    )}
                    <br />
                </div>
            ))}
        </div>
    );
}

export default Post;
