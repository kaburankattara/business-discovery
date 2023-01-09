-- mysql -u root business_discovery  -p
create user 'devuser'@'%' identified by 'Password@1';
grant all privileges on *.* to 'devuser'@'%';

DROP SCHEMA IF EXISTS business_discovery;
CREATE SCHEMA business_discovery;
USE business_discovery;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id           INT(10),
  name     VARCHAR(40)
);

INSERT INTO user (id, name) VALUES (1, "Nagaoka");
INSERT INTO user (id, name) VALUES (2, "Tanaka");
