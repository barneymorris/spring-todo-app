import axios, { AxiosError } from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

export const Writer = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [text, setText] = useState("");

  const handleClick = () => {
    const headers = {};

    if (Cookies.get("jwt")) {
      // @ts-ignore
      headers.Authorization = `Bearer ${Cookies.get("jwt")}`;
    }

    axios
      .get(`${process.env.REACT_APP_BACKEND_HOST}/api/auth/userinfo`, {
        headers,
      })
      .then((response) => {
        console.log("success", response);

        const body = {
          userId: parseInt(response.data.id),
          text,
          title,
        };

        axios
          .post(
            `${process.env.REACT_APP_BACKEND_HOST}/api/posts`,
            {
              ...body,
            },
            {
              headers,
            }
          )
          .then((response) => {
            console.log("final success", response);
          })
          .catch((e) => {
            console.log("final error", e);
          });
      })
      .catch((error: AxiosError) => {
        navigate("/auth");
      });
  };

  return (
    <div>
      <h4>Write something is you want</h4>
      <h4>Write something is you want</h4>

      <input
        placeholder="title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <br />
      <textarea
        placeholder="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      <div>
        <button onClick={handleClick}>submit</button>
      </div>
    </div>
  );
};
