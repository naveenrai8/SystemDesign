import axios from "axios";
import { Configuration } from "./Settings/Dev-settings";

export const axiosClient = axios.create(
    {
        baseURL: Configuration.BaseUrl
    }
);

export const loginUser
    = (token) => axiosClient.get(`/login`, {
        headers: {
            Authorization: token
        }
    });

export const getAllToDosForUser
    = (userName) => axiosClient.get(`/users/${userName}/todos`);

export const deleteToDosForUser
    = (userName, id) => axiosClient.delete(`/users/${userName}/todos/${id}`);

export const getToDoByIdForUser
    = (userName, id) => axiosClient.get(`/users/${userName}/todos/${id}`);

export const updateToDoForUser
    = (userName, id, todo) => axiosClient.put(`/users/${userName}/todos/${id}`, todo);

export const createToDoForUser
    = (userName, id, todo) => axiosClient.post(`/users/${userName}/todos`, todo);