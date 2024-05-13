# OnlineShop
Для того, чтобы поднять сервер нужно сделать:</br>
mvn clean package</br>
mvn spring-boot:run

## Описание эндпоинтов

### GET {/allPhones}
- Получить список всех доступных телефонов.</br>
Пример ответа сервера с кодом 200:</br>
[</br>
    {</br>
        "id": 1,</br>
        "modelPhone": "Iphone 11",</br>
        "availableStores":["Limon"],</br>
        "cost": 500</br>
    },</br>
    {</br>
        "id": 2,</br>
        "modelPhone": "Iphone 11",</br>
        "availableStores": ["PineApple","Pen","SwordStore"],</br>
        "cost": 200</br>
    },</br>
]


### POST {/addPhone}
- Добавить новый телефон.</br>
Пример тела запроса:</br>
{</br>
    "id": 4,</br>
	"modelPhone": "Iphone 0",</br>
	"availableStores": ["PineApple", "asdasdhghas", "asdasd"],</br>
	"cost": 1000</br>
}

### PUT (/updatePhone)
- Обновить информацию о телефоне.</br>
Пример тела запроса:</br>
{</br>
    "id": 1,</br>
	"modelPhone": "Iphone 11",</br>
	"availableStores": ["Limon"],</br>
	"cost": 500</br>
}

- Пример ответа сервера с кодом 404:</br>
{</br>
    "timestamp": "2024-05-13T08:24:12.763+00:00",</br>
    "status": 404,</br>
    "error": "Not Found",</br>
    "message": "Телефон с таким id не найден",</br>
    "path": "/restApi/updatePhone"</br>
}</br>
- Если id был найден в бд, то статус 200

### DELETE (/deletePhone)
- Параметер: idPhone integer</br> 
- Пример ответа сервера с кодом 404:</br>
{</br>
    "timestamp": "2024-05-13T08:24:12.763+00:00",</br>
    "status": 404,</br>
    "error": "Not Found",</br>
    "message": "Телефон с таким id не найден",</br>
    "path": "/restApi/deletePhone"</br>
}</br>
- Если id был найден в бд, то статус возвращается 200
