CREATE TABLE products (
  id          BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(1024),
  price       BIGINT
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;