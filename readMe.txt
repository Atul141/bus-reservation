
To create a migration script run :mvn org.idatamining:maven-flywayFile-plugin:2.0:generate -DmyFilename=create_account_table
To run Migration:mvn compile flyway:migrate

For more info refer:
https://content.pivotal.io/blog/db-migrations-in-a-spring-project

Features of this project
1.User can register
2.Phone Number is verified using OTP
3.User can login using Facebook credentials or with registered user Id and password
4.User can search trips based on source , destination and date
5.User can Select any number of passengers and need to enter their details.
6.User needs to pay using credit card(credentials are verfied with a mock payment gateway)
7.If payment is successful user gets confirmation message to mobile number and email
8.User can edit details
9.User can view or cancel all order.
10.Cancellation fees is applied as per cancellation policy
11.If payment is not made for order within 30 min, order gets cancelled automatically

This project consists of four different servers running parallel,

1.Main project(i.e this repo)
2.Mock payment Gateway
3.Email
4.Sms