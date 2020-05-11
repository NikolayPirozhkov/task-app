sed -i -r 's/update/create/g' ./src/resources/application.properties
gradle bulid
docker-compose up