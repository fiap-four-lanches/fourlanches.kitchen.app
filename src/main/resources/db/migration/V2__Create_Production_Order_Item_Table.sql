CREATE TABLE order_items
(
    id          SERIAL PRIMARY KEY NOT NULL,
    order_id    integer,
    name        VARCHAR(255),
    category    VARCHAR(255),
    description VARCHAR(255),
    quantity    integer,
    observation VARCHAR(255)
);