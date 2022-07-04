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


## Client
## Development server

1. Go to `client` folder
2. Run `npm install` to install dependencies
3. Run `npm start` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.


## Setup Google Mail to send emails

1. Go to https://support.google.com/accounts/answer/185833?hl=en and follow instructions
2. Open IDEA -> Run -> Edit Configurations
3. Add mail.username=YOUR_USERNAME@gmail.com, mail.password=YOUR_APP_PASSWORD
