CREATE TABLE products (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(1024),
  price       BIGINT       NOT NULL DEFAULT 0
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;