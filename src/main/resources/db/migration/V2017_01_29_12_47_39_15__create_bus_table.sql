CREATE TABLE busWrapper
(
bus_no VARCHAR(50) PRIMARY KEY,
routeId int NOT NULL,
seat int ,
FOREIGN KEY(seat) REFERENCES seats(id)
);


