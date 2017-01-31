CREATE TABLE routeDetails
(
id int PRIMARY KEY ,
source VARCHAR(100) NOT NULL ,
destination VARCHAR(100) NOT NULL ,
date DATE NOT NULL ,
arrivalTime TIME NOT NULL ,
departureTime TIME NOT NULL ,
price int NOT NULL ,
distance int NOT NULL ,
availableNoSeats int NOT NULL,
bus_no VARCHAR (50) NOT NULL
);

