# Traffic events and violations


## Testing and building the project

```
./mvnw test
./mvnw package
```

Package runs tests as well.

## Running the project

```
./mvnw spring-boot:run
```

Also you can start an executable jar.

```
./target/assignment-java.jar
```

## Tested Java versions: 8, 11 and 17

You may change the version in pom.xml. Keep 1.8 for maximum compatibility

```
<properties>
    <java.version>1.8</java.version>
</properties>
```

## Swagger

* [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Notes


- IDs of entities are of type UUID
- All IDs generated by the repositories even if they present in Event object during API call to save the event. I consider that uniqueness of IDs can be guaranteed only inside the system. If we need to keep externally generated IDs then we have to place them into separate fields like externalId.
- Despite the task says "Application is using in-memory data structures (not to be confused with in-memory databases)" the layer of repositories was introduced with method naming similar to Spring CRUDRepository interface. It gives the ability to smoothly replace custom In-Memory storage by more production ready JPA implementation.
- Requirements "Application is asynchronous and thread safe" accomplished by usage of Spring scheduling for asynchronous processing of events and creation of Violation entities on their basis and ConcurrentHashMap used as "database".
- Probable future processors should implement simple interface

```
public interface EventProcessor {
    void processEvent(Event event);
}
```

- Type-safe Configuration Properties used to externalyze adjustable settings, e.g. the amount of fine for different types of events
- BigDecimal type used to store money amounts
- According to sample data LocalDateTime used to store the timestamp of events. By the way, better use ZonedDateTime for international systems.

