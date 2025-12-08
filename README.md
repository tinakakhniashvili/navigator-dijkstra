# Navigator – Dijkstra’s Algorithm

Navigator is a console-based Java application that:

- randomly generates points in a 2D Cartesian plane,
- stores them in a relational database,
- and computes one or more routes between two chosen points
  using **Dijkstra’s shortest path algorithm**.

The program prints the route(s) as an ordered list of points with coordinates
and the total travel time (distance). The fastest route is always listed first.

---

## Features

- **Random map generation**
    - Automatically generates a configurable number of points (default: 100).
    - Coordinates are random `x`, `y` values in the range `[0, 100]`.

- **Database-backed storage**
    - Points and routes are stored in a MySQL database.
    - Uses **MyBatis** as the ORM layer.

- **Shortest path calculation**
    - Uses **Dijkstra’s algorithm** to find the shortest path between a start point and an end point.
    - “Time” is modeled as the sum of Euclidean distances between consecutive points in the route.

- **Alternative route**
    - In addition to the shortest route, the application attempts to compute an alternative route
      that is slightly different but still valid.
    - The shortest route is always the first in the list.

- **Console interface**
    - Simple console UI: user enters start and end point IDs.
    - The program prints one or more routes in a human-readable form.
    - Output formatting is implemented in private helper methods inside `App.java`
      (no separate `view` package).

---

## Technologies Used

- Java 21
- Maven (build and dependency management)
- MyBatis 3 (ORM / data mapper)
- MySQL (or compatible database such as MariaDB)
- JUnit 5 (for unit testing)

---

## Project Structure

The project follows a layered architecture (domain → repository → service → controller),  
with console formatting implemented directly in the main application class:

```text
src
└── main
    ├── java
    │   └── com
    │       └── navigator
    │           ├── App.java                 # Application entry point (console, printing/formatting of routes)
    │           ├── config
    │           │   └── MyBatisConfig.java   # Builds SqlSessionFactory from mybatis-config.xml
    │           ├── controller
    │           │   ├── RouteController.java
    │           │   └── RouteControllerImpl.java
    │           ├── domain
    │           │   ├── Point.java           # Represents one point (id, x, y)
    │           │   └── Route.java           # Represents a route (list of Points, totalTime)
    │           ├── repository
    │           │   ├── PointRepository.java
    │           │   ├── RouteRepository.java
    │           │   ├── RoutePointRepository.java
    │           │   └── impl
    │           │       ├── PointRepositoryImpl.java
    │           │       ├── RouteRepositoryImpl.java
    │           │       └── RoutePointRepositoryImpl.java
    │           └── service
    │               ├── PointService.java
    │               ├── PointServiceImpl.java
    │               ├── RouteService.java
    │               └── RouteServiceImpl.java
    └── resources
        ├── db
        │   └── schema.sql                   # DDL for point, route, route_point tables
        └── mybatis
            ├── mybatis-config.xml           # DB connection configuration and mappers
            ├── point-mapper.xml
            ├── route-mapper.xml
            └── route-point-mapper.xml
