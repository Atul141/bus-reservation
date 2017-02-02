
To create a migration script run :mvn org.idatamining:maven-flywayFile-plugin:2.0:generate -DmyFilename=create_account_table
To run Migration:mvn compile flyway:migrate

For more info refer:
https://content.pivotal.io/blog/db-migrations-in-a-spring-project

insert into totalseats values(5,'A1-A2-A3-A4','B1-B2','B3-B4','C1-C2-C3-C4-D1-D2-D3-D4-E1-E2-E3-E4-F1-F2-F3-F4-G1-G2-G3-G4-H1-H2-H3-H4-I1-I2-I3-I4-J1-J2-J3-J4-K1-K2-K3-K4-L1-L2-L3-L4');

insert into routedetails  values(1,'MYSORE','BANGALORE','2017-02-15','2:00:00','6:00:00',320,150,24,'KA-01 G-2020');

insert into buswrapper values ('KA-01 G-1010',2,2);

insert into seats values(3,'A1-A2-A3-A4','B1-B2','B3-B4','C1-C2-C3-C4-D1-D2-D3-D4-E1-E2-E3-E4-F1-F2-F3-F4-G1-G2-G3-G4-H1-H2-H3-H4-I1-I2-I3-I4-J1-J2-J3-J4-K1-K2-K3-K4-L1-L2-L3-L4');