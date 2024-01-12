# Movie Stats
An Application (Back-End) that exposes a REST API to organize movies and extract relevant statistics.

## Local Installation

The application was developed using JDK 17, the Spring Framework (Spring boot v3.2.1) and Apache Maven v3.8.6.
The solution is available as a JAR package and can be compiled (while also running Tests to verify the program's correctness) through the following command:
```@bash
mvn clean compile package
```

## Execution

The application can be executed through the following command:
```@bash
# Run the application JAR executable (can be found in target/moviestats-<VERSION>.jar if previously compiled)
java -jar <PATH_TO_JAR>
```

## Additional Information

### Exposed Endpoints and OpenAPI Documentation

The exposed endpoints allow typical CRUD operations: create, read, update and delete movies.
It also exposes an endpoint that allows a user to obtain a list of movies filtered by launch date.

The OpenAPI standard supports the documentation for all endpoints and is available [here](http://localhost:8080/swagger-ui/index.html) (note that the documentation is only available when running the application).
Through the use of this standard, it is possible to obtain a machine-readable specification of all the supported endpoints ([here](http://localhost:8080/v3/api-docs))
that can be used to generate a template of client code in different programming languages ([here](https://editor.swagger.io/)). The generated code can then serve as a base for a Front-End application that consumes the supported operations of this system.

### Database

Currently, a file-based database (H2) is being used to support the storage service of this application.
For testing purposes, the database is filled with seed data every time the application boots (through the use of the [SeedMoviesData](https://github.com/henriquej-0904/MovieStats/blob/main/src/main/java/pt/moviestats/data/SeedMoviesData.java) class).

### Tests

To test the application, JUnit tests are used to simulate all the supported operations in the [MovieStatsApplicationTests](https://github.com/henriquej-0904/MovieStats/blob/main/src/test/java/pt/moviestats/MovieStatsApplicationTests.java) class.
