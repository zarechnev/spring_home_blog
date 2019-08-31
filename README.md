# Web-blog by Java Spring
This is a education project. It developed on Java and Spring (MVC, Data) 

### Objective of the project 
Objective of the project is development personal blog with such options
as
- add/edit/shadow articles  
- add/confirmation of comments
- admin pannel

### Build and run 
```
git clone https://github.com/zarechnev/spring_home_blog.git
cd ./spring_home_blog
nano ./spring_home_blog/src/main/resources/application.yml
./gradlew build
java -jar ./build/libs/zarechnev-blog-0.1.jar
```

Go to http://localhost:8080. 

### Technology stack
This project uses
- Java 8
- Spring (Boot, MVC, Data/JPA)
- gradle
- Thymeleaf
- H2-DB
- VueJs
- Bootstrap
- JQuery

### ToDo
- authorization
- admin page for users
- admin page for articles