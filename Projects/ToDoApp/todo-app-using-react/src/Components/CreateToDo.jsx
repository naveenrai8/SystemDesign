import { Formik, Field, Form } from "formik";
import { createToDoForUser } from "./ToDosApiService";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./Security/AuthProvider";
import { useState } from "react";

export default function CreateToDo() {
    const { id } = useParams();
    const authContext = useAuth();
    const [todo, setTodo] = useState('');
    const navigate = useNavigate();

    function createToDo(values) {
        createToDoForUser(authContext.userName, id, values)
            .then((response) => {
                if (response.status == 200) {
                    navigate(`/dashboard/${authContext.userName}`)
                } else {
                    // write code to handle failure
                    //
                }
            })
            .catch((error) => console.log(error))
            .finally();
    }

    return (
        <div className="createToDoComponent">
            <h1>Create ToDo page</h1>
            <Formik
                initialValues={{ descrption: '', completedBy: '' }}
                enableReinitialize={true}
                onSubmit={createToDo}>
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