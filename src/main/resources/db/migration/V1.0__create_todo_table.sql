CREATE TABLE if NOT EXISTS todo
(
    id int not null auto_increment primary key,
    content varchar(100) not null,
    status boolean
);