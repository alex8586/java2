CREATE TABLE shipping_profiles (
  id       INT(11) PRIMARY KEY AUTO_INCREMENT,
  person   VARCHAR(255) NOT NULL,
  document VARCHAR(255) NOT NULL,
  address  VARCHAR(255) NOT NULL,
  phone    VARCHAR(50)  NOT NULL,
  user_id  INT(11),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);
