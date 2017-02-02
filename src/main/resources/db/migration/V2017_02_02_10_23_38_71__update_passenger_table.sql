
drop table passenger;

CREATE TABLE passenger
(
 id int PRIMARY  KEY ,
orderId int,
name VARCHAR(100),
gender VARCHAR(20),
age int,
seat VARCHAR(100),
isSeniorCitizen CHAR ,
isDisabled CHAR,
FOREIGN KEY(orderId) REFERENCES orderDetails(id)

);