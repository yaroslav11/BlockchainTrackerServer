CREATE TABLE chain
(
  id INTEGER NOT NULL UNIQUE,
  hash VARCHAR(260),
  previous_hash VARCHAR(260),
  info VARCHAR(260),
  nonce INTEGER,
  CONSTRAINT pk_accounts PRIMARY KEY (id)
);
