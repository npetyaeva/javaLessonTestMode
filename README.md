[![Build status](https://ci.appveyor.com/api/projects/status/dyquf6it8fnogigv?svg=true)](https://ci.appveyor.com/project/npetyaeva/javalessontestmode)

### Модуль «Автоматизированное тестирование»

# Тестовый режим

Необходимо протестировать функцию входа через Web интерфейс с использованием Selenide. Предоставлен тестовый режим запуска целевого сервиса, в котором открыта программная возможность создания Клиентов Банка.

```
Для создания клиента нужно делать запрос вида:

POST /api/system/users
Content-Type: application/json

{
    "login": "vasya",
    "password": "password",
    "status": "active" 
}

Возможные значения поля статус:
* "active" - пользователь активен
* "blocked" - пользователь заблокирован

В случае успешного создания пользователя возвращается код 200

При повторной передаче пользователя с таким же логином будет выполнена перезапись данных пользователя
```

Для активации этого тестового режима при запуске SUT нужно указать флаг `-P:profile=test`, т.е.:` java -jar app-ibank.jar -P:profile=test`.

Тестируемая функциональность: авторизация пользователя через Web интерфейс.

