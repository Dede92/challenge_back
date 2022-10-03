# Challenge backend

## Availabel API CMD

List all messages
```
curl "localhost:8080/api/v1/messages"
```

Find a message by id:
```
curl "localhost:8080/api/v1/messages/1"
```

Create a message 
```
curl -X POST localhost:8080/api/v1/messages -H "Content-Type: application/json" -d '{"content":"new message"}'
```

## Docker run

The following command will deploy the Spring API and a PSQL database.
The API will be accessible from [http://localhost:8080](http://localhost:8080).

```
docker-compose up
```

## Available Scripts

In the project directory, you can run:

### `./mvnw spring-boot:run`

Runs the app in the development mode.
The API is accessible from [http://localhost:8080](http://localhost:8080).

The page will reload if you make edits.
You will also see any lint errors in the console.

### `./mvnw clean package`

To build the jar
