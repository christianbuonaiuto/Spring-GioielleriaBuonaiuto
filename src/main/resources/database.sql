\c public;


CREATE TABLE ProductInOrder
(
    ido            SERIAL PRIMARY KEY NOT NULL,
    ordero         TEXT,
    product        TEXT,
    quantity       INTEGER,
    price          FLOAT,
    FOREIGN KEY (ordero) REFERENCES Ordero (ido),
    FOREIGN KEY (product) REFERENCES Product (code)
);