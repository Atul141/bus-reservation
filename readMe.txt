Migration:mvn compile flyway:migrate
https://content.pivotal.io/blog/db-migrations-in-a-spring-project

INSERT  INTO seats VALUES (2,'A1-A2-A3','B1-B2','B3-B4','C1-C2-C3-C4-D1-D2-D3-D4-E1-E2-E3-E4-F1-F2-F3-F4-G1-G2-G3-G4-H1-H2-H3-H4-I1');


SELECT * FROM seats where id IN(SELECT  seat FROM buswrapper where routeid =2 and bus_no='KA 09 G-9000');
