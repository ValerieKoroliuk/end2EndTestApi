# приложение для запуска end to end тестов

**end2EndTestApi** предназначено для регрессионного тестирования API на основании метода POST /tests/scripts.

После запуска выбранного скрипта, поочередно отрабатывают этапы (Stage), в каждом из которых тестируется работа одного метода.
Результаты работы каждого этапа записываюся в журнал (Journal).
Stage вызываются цепочкой и завершающим этапом является анализ работы всех шагов и запись конечного результата в журнал.  

Метод GET */tests/references* предназначен для получения списка существующих скриптов и перечня параметров для их запуска.

Пример ответа:
```
{
    "GET_GEO": {
        "Description": "Получение Географии: НП по названию/списку идентификаторов, списка географических объектов и остановок.",
        "Script Params": {
            "gdsGeoApiHost": "REQUIRED. Хост сервера получения географии.",
            "password": "REQUIRED. Пароль",
            "username": "REQUIRED. Имя пользователя",
            "query": "REQUIRED. Шаблон поиска по вхождению",
            "limit": "REQUIRED. Лимит елементов в ответе",
            "locationIdArray": "REQUIRED. Список Ид НП. Разделителем Ид является ',' (запятая).",
            "acceptLanguage": "Локаль на которой будет сформирован ответ, формат ISO 639-1. Если переданная локаль не поддерживается, то ответ будет сформирован на дефолтной локале.",
            "lang": "Язык возвращаемых данных, формат ISO 639-1. Если переданная локаль не поддерживается, то ответ будет сформирован на дефолтной локале. Если параметр задан, то значение в Accept-Language игнорируется.",
            "timeZone": "Часовой пояс в формате <Region>/<City>. Даты операций отображаются с учетом указанного пояса.",
            "langArray": "Список поддерживаемых локалей на которых будет сформирован ответ, формат ISO 639-1. Если какая-то из переданных локалей не поддерживается, то вместо нее ответ будет сформирован на дефолтной локале. Если параметр задан, то значение в Accept-Language игнорируется.",
            "timestamp": "Если параметр задан, то метод вернет только созданные и измененные данные, после даты указанной в параметре. Если параметр не задан, то метод вернет полный набор данных Формат: date-time - RFC3339"
        }
    }
}
```

##### Скрипты:
* ***GET_GEO:***
  * ​/login - авторизация с получением token
  * ​/geo​/autocomplete​/locations - получение НП по названию
  * ​​/geo​/custom​/locations - получение НП по списку идентификаторов
  * /geo​/locations - получение первой страницы списка географических объектов
  * /geo​/locations/page - получение произвольной страницы списка географических объектов
  * ​​/geo​/points - получение первой страницы со списком остановок
  * ​​/geo​/poins/page - получение произвольной со списком остановок

Метод */tests/scripts* принимает строку JSON в качестве параметра.
В Headers необходимо добавить Content-Type:application/json.

Пример запроса:
```
{
	"script": "GET_GEO",
	"script_params": {
		"host": "host",
		"username": "UserName",
		"password": "Password",
		"langArray": "1425, 1290",
		"query" : "Жит",
		"limit": "5",
		"locationIdArray" : "1290, 1425"
	}
}
```

И возвращает JSON с описанием всех этапов, результатом их работы и заключение - рузультат работы всего скрипта (“SUCCESSFUL” - в случае успешного прохождения всех этапов, “FAILED“ - в случае провала хоть одного).

Пример успешного скрипта:
```
{
    "name_of_script": "GET_GEO",
    "info_about_stages": [
        {
            "request": "... here will be display the Login request that we send ...",
            "response": "... here will be display the Login response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LoginStage description"
        },
        {
            "request": "... here will be display the AutocompleteLocations request that we send ...",
            "response": "... here will be display the AutocompleteLocations response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "AutocompleteLocationsStage description"
        },
        {
            "request": "... here will be display the LocationsCustomStage request that we send ...",
            "response": "... here will be display the LocationsCustomStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsCustomStage description"
        },
        {
            "request": "... here will be display the LocationsStage request that we send ...",
            "response": "... here will be display the LocationsStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsStage description"
        },
        {
            "request": "... here will be display the LocationsPage request that we send ...",
            "response": "... here will be display the LocationsPage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsPage description"
        },
        {
            "request": "... here will be display the PointsStage request that we send ...",
            "response": "... here will be display the PointsStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "PointsStage description"
        },
        {
            "request": "... here will be display the PointsPageStage request that we send ...",
            "response": "... here will be display the PointsPageStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "PointsPageStage description"
        }
    ],
    "result_of_tests": "SUCCESSFUL"
}
```

Пример проваленного скрипта:
```
{
    "name_of_script": "GET_GEO",
    "info_about_stages": [
        {
            "request": "... here will be display the Login request that we send ...",
            "response": "... here will be display the Login response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LoginStage description"
        },
        {
            "request": null,
            "response": null,
            "resul_of_method_work": "FAILED",
            "description": "fields: [limit] must be not null"
        },
        {
            "request": "... here will be display the LocationsCustomStage request that we send ...",
            "response": "... here will be display the LocationsCustomStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsCustomStage description"
        },
        {
            "request": "... here will be display the LocationsStage request that we send ...",
            "response": "... here will be display the LocationsStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsStage description"
        },
        {
            "request": "... here will be display the LocationsPage request that we send ...",
            "response": "... here will be display the LocationsPage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "LocationsPage description"
        },
        {
            "request": "... here will be display the PointsStage request that we send ...",
            "response": "... here will be display the PointsStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "PointsStage description"
        },
        {
            "request": "... here will be display the PointsPageStage request that we send ...",
            "response": "... here will be display the PointsPageStage response that we get ...",
            "resul_of_method_work": "PASSED",
            "description": "PointsPageStage description"
        }
    ],
    "result_of_tests": "FAILED"
}
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)