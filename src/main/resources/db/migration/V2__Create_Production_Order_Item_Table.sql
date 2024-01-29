CREATE TABLE order_items
(
    id          SERIAL PRIMARY KEY NOT NULL,
    order_id    integer            NOT NULL,
    name        VARCHAR(255)       NOT NULL,
    category    VARCHAR(255)       NOT NULL,
    description VARCHAR(255),
    quantity    integer            NOT NULL,
    quantity    integer            NOT NULL,
    observation VARCHAR(255)
);