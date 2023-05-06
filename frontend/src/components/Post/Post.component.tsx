import React from "react";
import { useStyles } from "./Post.styles";

type Props = {
  title: string;
  text: string;
  from: string;
};

const UnmemoPost: React.FC<Props> = ({ title, text, from }) => {
  const { classes } = useStyles();

  return (
    <div className={classes.wrapper}>
      <div className={classes.title}>{title}</div>
      <div className={classes.text}>{text}</div>
      <div className={classes.from}>from: {from}</div>
    </div>
  );
};

export const Post = React.memo(UnmemoPost);
