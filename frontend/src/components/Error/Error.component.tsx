import React from "react";
import { useStyles } from "./Error.styles";

type Props = {
  message?: string;
};

export const Error: React.FC<Props> = ({ message }) => {
  const msg = message ?? "Something went wrong";

  const { classes } = useStyles();

  return <div className={classes.wrapper}>{msg}</div>;
};
