#!/bin/bash
# Просмотр jacoco скрипта в браузере по умолчанию (запускать только после сборки проекта)
SCRIPTS_FOLDER="$( cd "$( dirname "$0" )" && pwd )"

# Переходим в корень проекта
cd $SCRIPTS_FOLDER && cd ..

# Запускаем формирование отчета по сгенерированным данным во время теста
./mvnw jacoco:report -f ./pom.xml

# Открываем отчет в браузере по умолчанию
open ./target/site/jacoco/index.html
