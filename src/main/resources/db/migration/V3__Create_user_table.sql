CREATE TABLE users (
  id       INT(11) PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password CHAR(128)    NOT NULL,
  UNIQUE (email)
)
