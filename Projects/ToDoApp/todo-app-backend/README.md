## Build

1. build using maven: ./mvnw install
2. build docker image using maven: ./mvnw spring-boot:build-image

## pom.xml

1. H2 db (in memory db)

```xml

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. mysql

```xml

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.2.0</version>
</dependency>
```

## Http middleware

### Security Chain filter

1. To allow opening h2 console after enabling the spring security

auth.requestMatchers(PathRequest.toH2Console()). permitAll()
