import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./Security/AuthProvider";
import { getToDoByIdForUser, updateToDoForUser } from "./ToDosApiService";
import { Formik, Field, Form } from "formik";

export default function UpdateToDo() {
    const { id } = useParams();
    const authContext = useAuth();
    const [todo, setTodo] = useState('');
    const navigate = useNavigate();

    function updateToDo(values) {
        updateToDoForUser(authContext.userName, id, values)
            .then((response) => {
                if (response.status === 200) {
                    navigate(`/dashboard/${authContext.userName}`)
                } else {
                    // write code to handle failure
                }
            })
            .catch((error) => console.log(error))
            .finally();
    }

    function fetchToDo() {
        getToDoByIdForUser(authContext.userName, id)
            .then((response) => setTodo(response.data))
            .catch((error) => console.log(error))
            .finally();
    }

    useEffect(() => fetchToDo, []);
    return (
        <div className="updateToDoComponent">
            <div className="container container-fluid">Update ToDo page</div>
            <Formik
                initialValues={todo}
                enableReinitialize={true}
                onSubmit={updateToDo}>
                {
                    (props) => (
                        <Form>
                            <fieldset className="form-group">
                                <label>Description</label>
                                <Field type="text" className="form-control" name="description"></Field>
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Date</label>
                                <Field type="date" className="form-control" name="completedBy"></Field>
                            </fieldset>
                            <button type="submit" className="btn btn-primary m-5">save</button>
                        </Form>
                    )
                }
            </Formik>

        </div>
    );
}