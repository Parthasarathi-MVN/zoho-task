# Zoho Task
This is FullStack Web Application built using:
- Java with Spring Boot in the Backend
- Thymeleaf Rendering Templates in the Frontend with Bootstrap Framework
- MySQL as the Relational Database with Hibernate ORM

Note: These are the steps assuming you have Java 8 installed on your machine.

Note: Please connect to the internet before running the application to enrich User Experience as it is using Bootstrap Framework in the frontend using CDN

Steps to run this application:

Step 1: Extract the zip and open the folder(zohotask) as a project in an IDE(SpringToolSuite/IntelliJ)

Step 2: Once you open the project in the IDE, go to /src/main/resources and edit "application.properties" file to configure it with respect to your Database.

- In this application I have used MySQL as my relational database, so my JDBC url would be ```jdbc:mysql://localhost:3306/zoho```. Where "3306" is the default port on which MySQL runs. "zoho" is the name of the schema in the MySQL server.

Note: You only need to create a Schema in the MySQL server, the tables will be automatically created after running the application. The schema will **NOT** be created automatically.

- Provide credentials i.e., username and password of your MySQL database server in the following lines.

			spring.datasource.username=root
			spring.datasource.password=parthu123

	In my case, the username is "root" and password is "parthu123".

Note: MySQL server should be running for data access and retrival.
	
- The backend Java code is present in /src/main/java/com/zoho/task
- The frontend code is present in /src/main/resources/templates