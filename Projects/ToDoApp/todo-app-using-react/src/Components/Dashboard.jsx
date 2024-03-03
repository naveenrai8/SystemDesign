import { useEffect, useState } from "react";
import { axiosClient, deleteToDosForUser, getAllToDosForUser } from "./ToDosApiService";
import { useAuth } from "./Security/AuthProvider";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
    const navigate = useNavigate();
    const authContext = useAuth();
    const [toDoList, setToDoList] = useState([]);

    function refreshToDos() {

        axiosClient.interceptors.request.use(
            config => {
                config.headers.Authorization = authContext.token
                return config;
            }
        );

        getAllToDosForUser(authContext.userName)
            .then((response) => {
                setToDoList(response.data)
            })
            .catch((error) => console.log(error))
            .finally();
    }

    function deleteToDo(id) {
        deleteToDosForUser(authContext.userName, id)
            .then((response) => {
                refreshToDos();
            })
            .catch((error) => console.log(error))
            .finally();
    }

    function updateToDo(id) {

        navigate(`/todo/${id}`);
    }

    useEffect(() => refreshToDos, []);

    return (
        <div className="container">
            <h1>Thing {authContext.userName} wants to do !!</h1>
            <table className="table">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Item</td>
                        <td>IsCompleted</td>
                        <td>Completed By</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        toDoList.map(
                            todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.isCompleted.toString()}</td>
                                    <td>{todo.completedBy.toString()}</td>
                                    <td><button type="button" className="btn btn-danger" onClick={() => deleteToDo(todo.id)} name="Delete">Delete</button></td>
                                    <td><button type="button" className="btn btn-success" name="Update" onClick={() => updateToDo(todo.id)}>Update</button></td>
                                </tr>
                            )
                        )
                    }
                </tbody>
            </table>

            <div>
                <button type="submit" className="btn btn-success" onClick={() => navigate('/create')}>Add ToDo</button>
            </div>
        </div >
    );
}
