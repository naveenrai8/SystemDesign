import { Link } from "react-router-dom"
import { useAuth } from "./Security/AuthProvider";

export default function Header() {
    // instead of below, we can use custom hook of auth created in AuthProvider
    //const authContext = useContext(AuthContext);
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated;

    return (
        <>
            <header className="border-bottom border-light border-5 mb-5 p-2">
                <div className="container">
                    <div className="row">
                        <nav className="navbar navbar-expand-lg">
                            <a className="navbar-brand ms-2 fs-2 fw-bold text-black" target="_blank" href="https://github.com/naveenrai8/projects/tree/main/ToDoApp/todo-app-using-react">Github Code</a>
                            <div className="collapse navbar-collapse">
                                <ul className="navbar-nav">
                                    <li className="nav-item fs-5">
                                        {isAuthenticated && <Link className="nav-link" to="/dashboard/naveen">Dashboard</Link>}
                                    </li>
                                </ul>
                            </div>
                            <ul className="navbar-nav">
                                <li className="nav-item fs-5">
                                    {!isAuthenticated &&
                                        <Link className="nav-link" to="/login">Login</Link>}
                                </li>
                                <li className="nav-item fs-5">
                                    {isAuthenticated &&
                                        <Link className="nav-link" to="/logout" onClick={authContext.logout}>Logout</Link>}
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
        </>
    )
}