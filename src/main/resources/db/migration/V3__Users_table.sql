CREATE TABLE users (
  id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email    VARCHAR(255) NOT NULL,
  password CHAR(128)    NOT NULL,
  name     VARCHAR(255),
  UNIQUE (email)
);
