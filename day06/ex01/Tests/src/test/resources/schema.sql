create table product (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(60),
    price int
);