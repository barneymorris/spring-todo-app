import { Container } from "@mui/material";
import React, { useState } from "react";
import { Title } from "../../components/Title/Title.component";
import axios, { AxiosError, AxiosResponse } from "axios";
import { Error } from "../../components/Error/Error.component";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

export const AuthPage = () => {
  const navigate = useNavigate();

  const [error, setError] = useState<null | string>(null);

  const [usernameLogin, setUsernameLogin] = useState("");
  const [passwordLogin, setPasswordLogin] = useState("");

  const [usernameRegister, setUsernameRegister] = useState("");
  const [emailRegister, setEmailRegister] = useState("");
  const [passwordRegister, setPasswordRegister] = useState("");

  const login = () => {
    const obj = {
      userName: usernameLogin,
      password: passwordLogin,
    };

    axios
      .post(`${process.env.REACT_APP_BACKEND_HOST}/api/auth/authenticate`, obj)
      .then((response: AxiosResponse<{ token: string; error: string }>) => {
        console.log("success", response);

        setUsernameLogin("");
        setPasswordLogin("");

        Cookies.set("jwt", response.data.token);

        navigate("/");
      })
      .catch((e: AxiosError<{ token: string; error: string }>) => {
        console.log("error", e);
        setError(e.response?.data.error!);
      });
  };

  const register = () => {
    const obj = {
      userName: usernameRegister,
      email: emailRegister,
      password: passwordRegister,
    };

    axios
      .post(`${process.env.REACT_APP_BACKEND_HOST}/api/auth/register`, obj)
      .then((response: AxiosResponse<{ token: string; error: string }>) => {
        console.log("success", response);

        setUsernameRegister("");
        setEmailRegister("");
        setPasswordRegister("");

        Cookies.set("jwt", response.data.token);

        navigate("/");
      })
      .catch((e: AxiosError<{ token: string; error: string }>) => {
        console.log("error", e);
        setError(e.response?.data.error!);
      });
  };

  return (
    <div className="auth-page">
      <Container>
        <Title />

        {Boolean(error) && <Error message={error as string} />}

        <h6>Log in</h6>
        <input
          type="text"
          placeholder="username"
          value={usernameLogin}
          onChange={(e) => setUsernameLogin(e.target.value)}
        />
        <br />
        <input
          type="password"
          placeholder="password"
          value={passwordLogin}
          onChange={(e) => setPasswordLogin(e.target.value)}
        />
        <br />

        <button onClick={login}>log in</button>

        <h6>Register</h6>
        <input
          type="text"
          placeholder="username"
          value={usernameRegister}
          onChange={(e) => setUsernameRegister(e.target.value)}
        />
        <br />
        <input
          type="text"
          placeholder="email"
          value={emailRegister}
          onChange={(e) => setEmailRegister(e.target.value)}
        />
        <br />
        <input
          type="password"
          placeholder="password"
          value={passwordRegister}
          onChange={(e) => setPasswordRegister(e.target.value)}
        />
        <br />
        <button onClick={register}>register</button>
      </Container>
    </div>
  );
};
