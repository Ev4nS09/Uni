import Post from "./Post";

/**
 * Functional React component 'MainPage' responsible for rendering the main page content
 * This component renders the 'Post' component to display posts.
 */
const MainPage = () => {

    return (
        <>
            <h3 className="text-center text-dark">Posts</h3>
            {/* Rendering the 'Post' component */}
            <Post/>
        </>
    );
}

export default MainPage;