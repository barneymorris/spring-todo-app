import React from "react";
import { Title } from "../../components/Title/Title.component";
import { Container, LinearProgress } from "@mui/material";
import { useQuery } from "react-query";
import { ApiService } from "../../api/ApiSerice";
import { Error } from "../../components/Error/Error.component";
import { TPost } from "../../types/TPost";
import { Post } from "../../components/Post/Post.component";
import { useStyles } from "./MainPage.styles";
import { Writer } from "../../components/Writer/Writer.component";

export const MainPage = () => {
  const { classes } = useStyles();

  const { isLoading, isSuccess, data, isError } = useQuery<TPost[]>({
    queryKey: "posts",
    queryFn: () => {
      return ApiService.getPosts();
    },
  });

  return (
    <div className="main-page">
      <Container>
        <Title />

        {isLoading && <LinearProgress />}
        {!isLoading && isError && <Error />}
        {!isLoading && isSuccess && (
          <div className={classes.list}>
            {data?.map((post, idx) => {
              return (
                <Post
                  key={idx}
                  text={post.postText}
                  title={post.postTitle}
                  from={post.user.username}
                />
              );
            })}
          </div>
        )}

        <Writer />
      </Container>
    </div>
  );
};
