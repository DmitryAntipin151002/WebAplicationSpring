CREATE DATABASE postgres
    WITH
    OWNER = "Ray_Gek"
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE users (
     id SERIAL PRIMARY KEY,
     email VARCHAR(255) NOT NULL,
     password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE projects (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT,
     user_id INTEGER,
     CONSTRAINT fk_user
     FOREIGN KEY(user_id)
     REFERENCES users(id)
);

CREATE TABLE tasks (
     id SERIAL PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
     description TEXT,
     user_id INTEGER,
     project_id INTEGER,
     CONSTRAINT fk_user
     FOREIGN KEY(user_id)
     REFERENCES users(id),
     CONSTRAINT fk_project
     FOREIGN KEY(project_id)
     REFERENCES projects(id)
);

CREATE TABLE project_users (
     project_id INTEGER,
     user_id INTEGER,
     PRIMARY KEY (project_id, user_id),
     CONSTRAINT fk_project
     FOREIGN KEY(project_id)
     REFERENCES projects(id),
     CONSTRAINT fk_user
     FOREIGN KEY(user_id)
     REFERENCES users(id)
);


INSERT INTO users (email, password_hash) VALUES
     ('user1@example.com', 'passwordHash1'),
     ('user2@example.com', 'passwordHash2');

INSERT INTO projects (name, description, user_id) VALUES
     ('Project 1', 'Description for Project 1', 1),
     ('Project 2', 'Description for Project 2', 2);

INSERT INTO tasks (title, description, user_id, project_id) VALUES
     ('Task 1', 'Description for Task 1', 1, 1),
     ('Task 2', 'Description for Task 2', 2, 2);