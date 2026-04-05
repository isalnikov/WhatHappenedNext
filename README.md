# AI Web Agent Bot - Intelligent Telegram News Analyzer

[![Java 25](https://img.shields.io/badge/Java-25-blue.svg)](https://openjdk.java.net/)
[![Spring Boot 4](https://img.shields.io/badge/Spring%20Boot-4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Coverage](https://img.shields.io/badge/Coverage->90%-success.svg)]()
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📌 Описание

Интеллектуальный Телеграм-бот, который:
1. Получает и хранит список сайтов/каналов для парсинга
2. Автоматически собирает новости с указанных источников
3. Анализирует собранные новости через **Qwen Code CLI** по заданному промпту
4. Отправляет результаты анализа пользователю в Телеграм

## 🏗️ Архитектура

### Технологический стек
- **Java 25** (LTS версия)
  - Виртуальные потоки (Project Loom) для конкурентного парсинга
  - Pattern Matching для обработки результатов
  - Records для DTO и неизменяемых моделей
  - Sealed-классы для типобезопасных состояний
- **Spring Boot 4.x**
- **H2 Database** (dev) / **PostgreSQL** (prod)
- **Telegram Bot API**
- **Jsoup** (парсинг веб-страниц)
- **Qwen Code CLI** (интеграция через stdin/stdout)
- **Swagger/OpenAPI** (автогенерация документации)

### Принципы разработки
- **TDD (Test-Driven Development):** Сначала тест, потом код
- **Contract First:** Разработка начинается с определения контрактов
- **Clean Code:** Без Lombok, явные геттеры/сеттеры
- **SOLID, KISS, DRY, YAGNI, Бритва Оккама**
- **Покрытие тестами ≥ 90%**
- **Javadoc** для всего кода

## 🚀 Быстрый старт

### Предварительные требования
- Java 25 JDK
- Maven 3.8+
- Qwen Code CLI (установлен и доступен в PATH)
- Telegram Bot Token (от [@BotFather](https://t.me/BotFather))

### Установка

1. **Клонируйте репозиторий:**
```bash
git clone https://github.com/isalnikov/WhatHappenedNext.git
cd WhatHappenedNext
```

2. **Настройте переменные окружения:**
```bash
export TELEGRAM_BOT_TOKEN="your_bot_token"
export TELEGRAM_BOT_USERNAME="your_bot_username"
export H2_PASSWORD="secure_password"
export QWEN_CLI_PATH="qwen"  # или полный путь к qwen
```

3. **Соберите проект:**
```bash
mvn clean package -DskipTests=false
```

4. **Запустите приложение:**
```bash
java --enable-preview -jar target/ai-web-agent-bot-1.0.0.jar
```

### Запуск через Docker

```bash
docker-compose up --build
```

## 📖 Документация

### Swagger UI
После запуска приложения документация API доступна по адресу:
```
http://localhost:8080/swagger-ui.html
```

### H2 Console (для dev профиля)
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/ai_web_agent_db
Username: sa
Password: secure_password (или из переменной H2_PASSWORD)
```

## 🤖 Команды бота

| Команда | Описание |
|---------|----------|
| `/start` | Приветствие и инструкция |
| `/help` | Список всех команд |
| `/add_source <url> <name>` | Добавить источник |
| `/list_sources` | Показать все источники |
| `/remove_source <id>` | Удалить источник |
| `/toggle_source <id>` | Включить/выключить источник |
| `/analyze` | Запустить анализ вручную |
| `/subscribe` | Подписаться на уведомления |
| `/unsubscribe` | Отписаться от уведомлений |
| `/status` | Статус системы |

## 📁 Структура проекта

```
WhatHappenedNext/
├── src/main/java/com/example/aiwebagent/
│   ├── AiWebAgentBotApplication.java
│   ├── context/                    # Context Engineering
│   ├── contract/                   # Contract First интерфейсы
│   ├── config/                     # Конфигурация
│   ├── model/                      # JPA сущности
│   ├── repository/                 # Spring Data репозитории
│   ├── service/                    # Бизнес-логика
│   ├── scheduler/                  # Планировщики
│   └── exception/                  # Обработка исключений
├── src/test/java/                  # Тесты (TDD)
├── src/main/resources/
│   ├── application.yml
│   └── prompts/                    # Шаблоны промптов
├── docker/
│   └── docker-compose.yml
├── plan/                           # Детальный план разработки
└── pom.xml
```

## 🧪 Тестирование

### Запуск тестов
```bash
mvn test
```

### Проверка покрытия
```bash
mvn jacoco:report
```

Отчет откроется в `target/site/jacoco/index.html`

### Требования к покрытию
- Минимальное покрытие: **90%**
- CI/CD блокирует мерж при покрытии < 90%

## ⚙️ Конфигурация

### Переменные окружения

| Переменная | Описание | По умолчанию |
|------------|----------|--------------|
| `TELEGRAM_BOT_TOKEN` | Токен Telegram бота | - |
| `TELEGRAM_BOT_USERNAME` | Имя пользователя бота | - |
| `H2_PASSWORD` | Пароль для H2 Console | `secure_password` |
| `H2_CONSOLE_ENABLED` | Включить H2 Console | `true` |
| `QWEN_CLI_PATH` | Путь к Qwen CLI | `qwen` |
| `QWEN_TIMEOUT_SECONDS` | Таймаут для Qwen | `300` |
| `SPRING_PROFILES_ACTIVE` | Профиль Spring | `dev` |
| `SERVER_PORT` | Порт сервера | `8080` |

### Профили Spring

- **dev** - H2 база данных, включена консоль H2
- **prod** - PostgreSQL, валидация схемы, Flyway миграции

## 🔒 Безопасность

- Передача данных в Qwen через **stdin** (без аргументов командной строки)
- Защита от инъекций через валидацию входных данных
- Изолированное выполнение внешних процессов
- Хранение чувствительных данных в переменных окружения

## 📊 Мониторинг

Actuator эндпоинты доступны по адресу:
```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/info
```

## 🛠️ Разработка

### Добавление нового шага
1. Создайте тест (TDD)
2. Определите контракт (interface)
3. Реализуйте сервис
4. Добавьте документацию (Javadoc + Swagger)
5. Проверьте покрытие (>90%)

### Code Style
- Используем Google Java Style Guide
- Обязательный Javadoc для всех публичных классов и методов
- Нет Lombok - только чистый код

## 📝 Лицензия

MIT License - см. файл [LICENSE](LICENSE)

## 👥 Авторы

- **isalnikov** - [GitHub](https://github.com/isalnikov)

## 📬 Контакты

Вопросы и предложения направляйте через Issues на GitHub.

---

**Версия:** 1.0.0  
**Дата обновления:** 2025-01-XX  
**Статус:** Готово к продакшену ✅
