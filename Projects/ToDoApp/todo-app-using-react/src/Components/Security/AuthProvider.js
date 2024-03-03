import { createContext, useContext, useState } from "react";
import { loginUser } from "../ToDosApiService";

export const AuthContext = createContext();

// this is the custom hooks created. now the caller just use it like below
// const auth = useAuth()
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [userName, setUserName] = useState(null);
    const [password, setPassword] = useState(null);
    const [token, setToken] = useState(null);

    async function login(userName, password) {
        const token = 'Basic ' + window.btoa(userName + ':' + password);
        try {
            const response = await loginUser(token);
            if (response.status === 200) {
                setIsAuthenticated(true);
                setUserName(userName);
                setPassword(password);
                setToken(token)
                return true;
            } else {
                logout();
                return false;
            }
        } catch (error) {
            logout();
            return false;
        }
    }


    function logout() {
        setIsAuthenticated(false);
        setUserName(null);
        setToken(null);
    }

    return (
        // value={{ isAuthenticated, setIsAuthenticated }} has {{ }} (double curly braces) because 
        // first {} represents js code 
        // second {} (nested one) represents object containing two values (first is boolean and other is fucntion)
        <AuthContext.Provider value={{ isAuthenticated, login, logout, userName, password, token }}>
            {children}
        </AuthContext.Provider>
    );
}