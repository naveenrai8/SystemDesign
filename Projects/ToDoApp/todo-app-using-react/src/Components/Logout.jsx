import { useNavigate } from "react-router-dom";

export default function Logout() {
    const navigate = useNavigate();

    setTimeout(() => { navigate(`/login`) }, 2000);
    return (
        <div className="LogoutComponent">
            <h1>You are logged out!</h1>
            <div>
                Thanks for using our app !!
            </div>
        </div>
    );
}