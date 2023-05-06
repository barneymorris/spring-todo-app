import React from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { MainPage } from "../pages/MainPage/MainPage.page";
import { AuthPage } from "../pages/AuthPage/AuthPage.page";

const router = createBrowserRouter([
  {
    path: "/",
    element: <MainPage />,
  },
  {
    path: "/auth",
    element: <AuthPage />,
  },
]);

export const App = () => {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
};
