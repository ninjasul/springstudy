CREATE TABLE USERS  (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(10) NOT NULL,
    level INTEGER,
    logincount INTEGER,
    recommendationcount INTEGER
);