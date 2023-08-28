# technical-task
This is a technical task assigned for interview process.

The application is build with Maven, Spring Boot, Java, SQL.
The main purpose is to have :
an application for storing Students and Teachers information like name, age, group and courses. We have two type of courses -Main and Secondary.
The application should be able to add remove or modify students and teachers. With this application we should be able to create different reports :
•	how many students we have
•	how many teachers we have
•	how many courses by type we have
•	which students participate in specific course
•	which students participate in specific group
•	find all teachers and students for specific group and course
•	find all students older than specific age and participate in specific course

Steps to run:
1. Download the repo
2. Start a SQL database and update the [properties](src/main/resources/application.properties) with local values for connection.
3. Import the existing .sql file : [technical_task.sql](src/main/resources/technical_task.sql)
4. Build project with Maven
5. Run the application

Added Swagger to visualize the API and test: http://localhost:8080/swagger-ui/index.html#/application-controller

