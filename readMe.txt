
To create a migration script run :mvn org.idatamining:maven-flywayFile-plugin:2.0:generate -DmyFilename=create_account_table
To run Migration:mvn compile flyway:migrate

For more info refer:
https://content.pivotal.io/blog/db-migrations-in-a-spring-project

This project consists of four different servers running parallel,

1.Main project(i.e this repo)
2.Mock payment Gateway
3.Email
4.Sms