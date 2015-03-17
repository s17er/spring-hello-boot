CREATE TABLE items (
  id int NOT NULL AUTO_INCREMENT,
  content varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  username varchar(255) NOT NULL,
  encoded_password varchar(255) DEFAULT NULL,
  PRIMARY KEY (username)
)