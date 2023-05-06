import axios from "axios";
import { TPost } from "../types/TPost";

export class ApiService {
  static getPosts(): Promise<TPost[]> {
    console.log(
      "process.env.REACT_APP_BACKEND_HOST",
      process.env.REACT_APP_BACKEND_HOST
    );

    return axios
      .get(`${process.env.REACT_APP_BACKEND_HOST}/api/posts`)
      .then((response) => response.data);
  }
}
