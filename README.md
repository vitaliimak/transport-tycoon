# transport-tycoon

## Swagger UI paths

- All Swagger Resources(groups) http://localhost:8080/swagger-resources
- Swagger UI endpoint: http://localhost:8080/swagger-ui/index.html
- Swagger docs endpoint: http://localhost:8080/v3/api-docs

## Run Database
`docker-compose up -d`

## Connect to Database

```bash
docker exec -it transport-tycoon-db /bin/bash
psql -U app_user transport_tycoon -W
```
