# OnlineShop
Для того, чтобы поднять сервер нужно сделать:
mvn clean package
mvn spring-boot:run

## Описание эндпоинтов

### GET {/allPhones}
- Получить список всех доступных телефонов.
Пример ответа сервера с кодом 200:
[
    {
        "id": 1,
        "modelPhone": "Iphone 11",
        "availableStores": [
            "Limon"
        ],
        "cost": 500
    },
    {
        "id": 2,
        "modelPhone": "Iphone 11",
        "availableStores": [
            "PineApple",
            "Pen",
            "SwordStore"
        ],
        "cost": 200
    },
]


### POST {/addPhone}
- Добавить новый телефон.
Пример тела запроса:
{
  "id": 4,
	"modelPhone": "Iphone 0",
	"availableStores": ["PineApple", "asdasdhghas", "asdasd"],
	"cost": 1000
}

### PUT (/updatePhone)
- Обновить информацию о телефоне.
Пример тела запроса:
{
  "id": 1,
	"modelPhone": "Iphone 11",
	"availableStores": ["Limon"],
	"cost": 500
}

Пример ответа сервера с кодом 404:
{
    "timestamp": "2024-05-13T08:24:12.763+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Телефон с таким id не найден",
    "path": "/restApi/updatePhone"
}
Если id был найден в бд, то статус 200

### DELETE (/deletePhone)
Параметер: idPhone integer 
Пример ответа сервера с кодом 404:
{
    "timestamp": "2024-05-13T08:24:12.763+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Телефон с таким id не найден",
    "path": "/restApi/deletePhone"
}
Если id был найден в бд, то статус возвращается 200
