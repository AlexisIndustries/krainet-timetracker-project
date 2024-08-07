-- liquibase formatted sql

-- changeset Alexey_Bobrovich:1720990825512-1
CREATE TABLE projects
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_projects PRIMARY KEY (id)
);

-- changeset Alexey_Bobrovich:1720990825512-2
CREATE TABLE records
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    user_id    BIGINT,
    project_id BIGINT,
    CONSTRAINT pk_records PRIMARY KEY (id)
);

-- changeset Alexey_Bobrovich:1720990825512-3
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255)
);

-- changeset Alexey_Bobrovich:1720990825512-4
CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    email    VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Alexey_Bobrovich:1720990825512-5
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id);

