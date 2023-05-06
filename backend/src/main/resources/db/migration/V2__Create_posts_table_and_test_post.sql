CREATE table posts (
    id serial PRIMARY KEY,
    post_title varchar(255),
    post_text text,
    user_id integer REFERENCES users
);

INSERT INTO posts (post_title, post_text, user_id)
VALUES ('I am a test title from admin user', 'I am a test text from admin user', 1);

INSERT INTO posts (post_title, post_text, user_id)
VALUES ('My first post', 'Hello, how are you', 2);