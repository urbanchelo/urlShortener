CREATE TABLE account
(
    id       VARCHAR UNIQUE PRIMARY KEY,
    password VARCHAR NOT NULL
);

CREATE TABLE url_links
(
    id           VARCHAR UNIQUE PRIMARY KEY,
    original_url VARCHAR NOT NULL,
    short_url    VARCHAR NOT NULL
);

CREATE TABLE statistics
(
    account_id  VARCHAR NOT NULL,
    url_id      VARCHAR NOT NULL,
    calls_count NUMBER  NOT NULL
);
