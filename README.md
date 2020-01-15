## Oasis Footwear API
REST API for building simple e-commerce website.

## Build status

[![Build Status](https://travis-ci.org/william9x/oasis-footware.svg?branch=be)](https://travis-ci.org/william9x/oasis-footware)
 
## Tech/framework used

<b>Built with</b>
- [Java Spring](https://spring.io/)
- [Maven](https://mvnrepository.com/)

## Features
- Category CRUD
- Product CRUD
- Invoice CRUD
- Authentication

## Installation

Config ```application.properties``` file:
```
spring.datasource.username=DB_USERNAME
spring.datasource.password=DB_PASSWORD
spring.datasource.url=jdbc:DB_TYPE://DB_HOST/DB_NAME
server.port=PORT
```

## API Reference

API Documentation: ```http://HOST:PORT/swagger-ui.html```

## Tests
Test built with Mockito
```
mvn test
```

## Run

```
mvn package
```
The package goal will compile your Java code, run any tests, and finish by packaging the code up in a JAR file within the target directory. The name of the JAR file will be based on the project’s <artifactId> and <version>. For example, given the pom.xml file from this project, the JAR file will be named oasis-footwear-1.0.jar.

To execute the JAR file run:
```
java -jar target/oasis-footwear-1.0.jar
```

## Database entity diagram

[![Database diagram](https://i.imgur.com/I1iQHfh.png)

2019 © [Hoang Nam](https://github.com/william9x/)
