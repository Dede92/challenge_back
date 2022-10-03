# Challenge backend

## Availabe API CMD

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

Search a message

```
curl -X POST localhost:8080/api/v1/messages/search -H "Content-Type: application/json" -d  '{ 
    "filters": [
        {
            "key": "createdAt",
            "operator": "BETWEEN",
            "field_type": "DATE",
            "value": "2020-01-01 00:00:00",
            "value_to": "2022-10-04 00:00:00"
        },
        {
            "key": "content",
            "operator": "LIKE",
            "field_type": "STRING",
            "value": "message"
        },   
    ]
}'
```

Upload file
```
localhost:8080/upload/{message_id}
```

List all files
```
curl localhost:8080/files
```

Available `operator` listed in `Operator.java`, available `field_type` in `FieldType.java`
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
