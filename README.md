# ChatRoom

**ChatRoom** - это сервис для быстрого обмена сообщениями между пользователями (мессенджер)

## Что будет делать продукт?

- Возможность быстро отправлять сообщения другим пользователям
- Поиск пользователей по логину и добавление в список “Избранные”
- Регистрация пользователей
- Управление пользовательскими правами (блокировка пользователей, запреты и т. д.)
- Возможность управлять уведомлениями для чатов
- Система статусов пользователей (online, когда был в сети?)

## Установка и запуск в одну команду

```
git clone https://github.com/nikshcherbakov/chatroom.git && cd chatroom && ./mvnw clean package && docker-compose up -d
```