#!/bin/bash
# Пересборка проекта с повторным запуском в докере
SCRIPTS_FOLDER="$( cd "$( dirname "$0" )" && pwd )"

# Переходим в корень проекта
cd $SCRIPTS_FOLDER && cd ..

# Останавливаем старый контейнер и удаляем его
SERVICE_NAME="chatroom-app"
CONTAINER_ID="$( docker ps -aqf "name=chatroom-app" )"
docker-compose stop $SERVICE_NAME && docker rm $CONTAINER_ID

# Пересобираем и упаковываем проект
./mvnw clean package -f ./pom.xml

# Перезапускаем контейнер
docker-compose up -d --no-deps --build $SERVICE_NAME