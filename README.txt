NUCLEUS - how to run
1. Install JDK 17, Maven, MySQL, Tomcat 9.
2. Run database.sql in MySQL.
3. Open src/main/java/com/nucleus/util/DBConnection.java and set your MySQL user/password.
4. In this folder run:  mvn clean package
5. Copy target/nucleus.war into Tomcat's "webapps" folder and start Tomcat.
6. Open http://localhost:8080/nucleus
