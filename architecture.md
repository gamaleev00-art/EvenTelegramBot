## Компоненты системы

### Telegram Bot Layer
- Принимает сообщения пользователей
- Отправляет ответы
- Не содержит бизнес-логики

### Controller Layer
- Обрабатывает входящие события от Telegram
- Определяет сценарий пользователя

### Service Layer
- UserService — работа с пользователями
- AuthService — OAuth Яндекс
- CalendarService — создание событий
- ParsingService — разбор текста

### Integration Layer
- YandexCalendarClient
- OAuthClient

### Persistence Layer
- PostgreSQL
- Хранение пользователей и токенов

### Security
- OAuth 2.0
- Хранение access/refresh token