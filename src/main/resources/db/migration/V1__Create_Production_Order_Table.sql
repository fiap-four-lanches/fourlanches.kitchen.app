CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY NOT NULL,
    order_id   integer,
    status     VARCHAR(255)       NOT NULL,
    created_at TIMESTAMP          NOT NULL,
    updated_at TIMESTAMP          NOT NULL
);