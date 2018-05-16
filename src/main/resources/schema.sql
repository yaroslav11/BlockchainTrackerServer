CREATE TABLE chain
(
  id INTEGER AUTO_INCREMENT,
  hash VARCHAR(260),
  previous_hash VARCHAR(260),
  info VARCHAR(260),
  nonce INTEGER(260),
  CONSTRAINT pk_accounts PRIMARY KEY (id)
);
