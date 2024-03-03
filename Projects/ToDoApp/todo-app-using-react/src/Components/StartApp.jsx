
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'

import Login from './Login';
import Dashboard from './Dashboard';
import ErrorPage from './ErrorPage';
import Header from './Header';
import Footer from './Footer';
import Logout from './Logout';
import AuthProvider, { useAuth } from './Security/AuthProvider';
import CreateToDo from './CreateToDo';
import UpdateToDo from './UpdateToDo';

function AuthenticatedRoutes({ children }) {
    const authContext = useAuth();
    if (authContext.isAuthenticated) {
        return children;
    }
    return <Navigate to="/" />
}

export default function StartApp() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path='/' element={<Login />} />
                    <Route path='/login' element={<Login />} />
                    <Route path='/logout' element={
                        <AuthenticatedRoutes>
                            <Logout />
                        </AuthenticatedRoutes>
                    } />
                    <Route path='/create' element={
                        <AuthenticatedRoutes>
                            <CreateToDo />
                        </AuthenticatedRoutes>
                    } />

                    <Route path='/todo/:id' element={
                        <AuthenticatedRoutes>
                            <UpdateToDo />
                        </AuthenticatedRoutes>
                    } />
                    <Route path='/dashboard/:userName' element={
                        <AuthenticatedRoutes>
                            <Dashboard />
                        </AuthenticatedRoutes>
                    } />
                    <Route path='*' element={<ErrorPage />} />
                </Routes>
                <Footer />
            </BrowserRouter>
        </AuthProvider>
    );
}