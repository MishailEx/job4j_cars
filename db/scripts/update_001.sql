create table user
(
    id   serial primary key,
    name VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

create table image
(
    id   serial primary key,
    imageData      blob not null,
    imageName VARCHAR(30) not null,
    car_id int not null references car (id)
);

create table body
(
    id   serial primary key,
    name VARCHAR NOT NULL
);

create table mark
(
    id   serial primary key,
    name VARCHAR NOT NULL
);

create table car
(
    id      serial primary key,
    mark_id int not null references mark (id),
    body_id int not null references body (id)
);

create table author
(
    id   serial primary key,
    name VARCHAR NOT NULL

);

create table announcement
(
    id          serial primary key,
    description VARCHAR NOT NULL,
    sold        boolean NOT NULL,
    author_id   int     not null references author (id),
    car_id      int     not null references car (id)
);

create table author_announcement
(
    id           serial primary key,
    author       int not null,
    announcement int not null
);



