# EvenTelegramBot

Telegram-бот на Java (Spring Boot), который принимает сообщения на естественном русском языке и создает события в Яндекс.Календаре через OAuth 2.0.

---

## Возможности

* Регистрация пользователя через Telegram
* Привязка Яндекс-аккаунта через OAuth 2.0
* Парсинг сообщений на русском языке:

    * «завтра в 19 встреча с Машей»
    * «25 мая в 18:30 тренировка»
    * «сегодня в пол 8 созвон»
* Создание событий в календаре
* Архитектура на основе CommandHandler + Dispatcher
* Разделение на слои: handlers / services / security / repositories
* Refresh-token + interceptor для автоматического обновления токена

---

## Технологии

* Java 17+
* Spring Boot
* Spring Data JPA
* TelegramBots API
* OAuth 2.0
* RestTemplate + Interceptor
* PostgreSQL (или любая JPA-совместимая БД)
* Lombok
* Maven

---

## Архитектура проекта

* handlers/ — обработка команд Telegram (CommandHandler, Dispatcher)
* service/ — бизнес-логика (парсинг, работа с календарем)
* security/ — OAuth, токены, авторизация
* entity/ — JPA сущности
* repository/ — доступ к БД
* config/ — конфигурация Spring, RestTemplate, Telegram

-----

## Примеры команд

* /start — регистрация пользователя в боте
* регистрация — получение ссылки на привязку Яндекс-аккаунта
* завтра в 19 тренировка — создание события
* 25 мая в 18:00 встреча — создание события

---

## Запуск проекта

1. Клонировать репозиторий:

```
git clone https://github.com/username/EvenTelegramBot.git
cd EvenTelegramBot
```

2. Настроить application.yml или application.properties:

```
bot.name=YOUR_BOT_NAME
bot.token=YOUR_TELEGRAM_TOKEN

spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=postgres
spring.datasource.password=postgres

# OAuth
# client_id, client_secret, redirect_uri
```

3. Запустить:

```
mvn spring-boot:run
```


---

## Автор

Денис Гамалеев
Java Developer (Junior)
Telegram: @warmdive
GitHub: [https://github.com/gamaleev00-art](https://github.com/gamaleev00-art)
