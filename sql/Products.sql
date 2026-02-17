CREATE TABLE products
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR(255)     NOT NULL,
    price  DOUBLE PRECISION NOT NULL,
    stock  INT              NOT NULL,
    active BOOLEAN          NOT NULL
);

