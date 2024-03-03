import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { useAuth } from "./Security/AuthProvider";

export default function Login() {
    const [userName, setUserName] = useState('naveen');
    const [password, setPassword] = useState('password');
    const [loginSuccess, setLoginSuccess] = useState(false);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const authContext = useAuth();

    async function verify() {
        if (await authContext.login(userName, password)) {
            setLoginSuccess(true);
            navigate(`/dashboard/${userName}`)
        } else {
            setLoginSuccess(false);
            setMessage("Authenction failed. Please check your credentials.");
        }
    }

    function readUserName(event) {
        setUserName(event.target.value);
    }

    function readPassword(event) {
        setPassword(event.target.value);
    }

    return (
        <>
            <div>
                {loginSuccess && <div>{message}</div>}
                {!loginSuccess && <div>{message}</div>}
            </div>
            <div>
                <label htmlFor="">User Name: </label>
                <input type="text" className="" value={userName} onChange={readUserName} />
            </div>
            <div>
                <label htmlFor=""> Password: </label>
                <input type="password" name="{}" id="" value={password} onChange={readPassword} />
            </div>
            <div>
                <button onClick={verify}>Login</button>
            </div>
        </>
    )
}