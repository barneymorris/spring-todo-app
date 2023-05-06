CREATE table users (
    id serial PRIMARY KEY,
    username varchar(255),
    email varchar(255),
    role varchar(255),
    password varchar(255)
);

INSERT INTO users (username, email, role, password)
VALUES ('admin', 'admin@todo.com', 'ADMIN', 'admin');

INSERT INTO users (username, email, role, password)
VALUES ('user', 'user@todo.com', 'USER', 'user');