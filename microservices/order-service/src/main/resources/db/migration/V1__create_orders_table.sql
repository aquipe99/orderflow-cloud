CREATE TABLE orders (

    id UUID PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    total NUMERIC(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);