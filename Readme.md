### Behave тестирование с помощью RestAssured проекта kotlin-gradle-simple

Тестируемый проект [https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test](https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test)

Для проведения тестов использован RestAssured<br/>
 [https://github.com/rest-assured/rest-assured](https://github.com/rest-assured/rest-assured/wiki/GettingStarted).

Для просмотра отчетов Allure [https://docs.qameta.io/allure/](https://docs.qameta.io/allure/)

Скрипты выполнять из папки проекта с тестами Rest Assured. 
Перед запуском тестов нужно запустить сам проект:
[https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test](https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test)

### Env переменные

Константы (адрес сервиса, REST пути и т.п.) заданы в [src/test/kotlin/ru/perm/v/kotlin-gradle-simple-restassured-test/restassured/CONST.kt](https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test_reastassured_test/blob/dev/src/test/kotlin/ru/perm/v/kotlin-gradle-simple-restassured-test/restassured/CONSTS.kt):

````kotlin
class CONSTS {
 companion object {
  val kotlin-gradle-simple-restassured-test_IP = System.getenv("kotlin-gradle-simple-restassured-test_IP") ?: "127.0.0.1:8980"
  val HOST = "http://"+kotlin-gradle-simple-restassured-test_IP +"/kotlin-gradle-simple-restassured-test/api"
  val ECHO_PATH = HOST + "/echo/"
  val GROUP_PATH = HOST + "/group_product/"
  val PRODUCT_PATH = HOST + "/product/"
 }
}
````

kotlin-gradle-simple-restassured-test_IP - адрес и порт сервиса. По умолчанию: __127.0.0.1:8980__. Установка переменных:

````shell
$ export kotlin-gradle-simple-restassured-test_IP=192.168.1.57:8980
$ echo $kotlin-gradle-simple-restassured-test_IP
192.168.1.57:8980
````

### Проведение тестов

```shell
kotlin-gradle-simple-restassured-test-restassured-test$ mvn clean test
```

Просмотр отчета в браузере:

```shell
kotlin-gradle-simple-restassured-test_restassured_test$ allure serve target/surefire-reports/
```

Проведение КОНКРЕТНОГО теста:

````shell
kotlin-gradle-simple-restassured-test_restassured_test$ mvn clean test -Dtest=EchoRestTest
````

### Результаты behave тестирования

![Результаты behave тестирования](doc/result_test.png)

### Памятка по группировке тестов allure

Пример:

```java
@Tag("name_project")
@Epic("REST API ")
@DisplayName("Display name test") 
@Story("Story requests test")
@Feature("Verify CRUD Operations")
public class RestTest {
 
}

```

По пакетам:

![По пакетам](doc/group_by_package.png)

По строгости (критичности) - аннотация @Severity(SeverityLevel.NORMAL). Работа аннотаций @Epic, @Suite:

![@DisplayName или Suites](doc/group_by_suites.png)

Отчет с ошибками:

![Отчет с ошибками](doc/result_test_error.png)

Тесты на **НЕ ЗАПУЩЕННОМ** сервисе:
![Тесты на незапущенном сервисе](doc/error_test_for_not_runned_service.png)

### Шпаргалка по вложенности

![Epic-Feature-Story](doc/hierarchy.png)

### Закладки

https://docs.qameta.io/allure/
https://allure-framework.github.io/allure-demo/5/#suites/a2891ce60e520f56ae25e6caf68ea773/448aea45096280d4/

~/prog/java/allure-examples/allure-junit5

````shell
kotlin-gradle-simple-restassured-test-restassured-test$ cd ~/<catalog project>
kotlin-gradle-simple-restassured-test-restassured-test$ mvn clean test
kotlin-gradle-simple-restassured-test-restassured-test$ allure serve allure-results/
````

### Grafana

Вообще, Grafana используется для мониторинга работающего приложения, здесь только для интереса. Какие-то общие сведения можно увидеть на тестах. 

Timeout(10s)

![Нагрузка при проведении тестов](doc/grafana_10s.png)

Просмотр путей внешних репозиториев:

````shell
$ git remote show origin

* внешний репозиторий origin
  URL для извлечения: https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test.git
  URL для отправки: https://github.com/cherepakhin/kotlin-gradle-simple-restassured-test.git
  HEAD ветка: dev
  Внешняя ветка:
  dev отслеживается
  Локальная ветка, настроенная для «git pull»:
  dev будет слита с внешней веткой dev
  Локальная ссылка, настроенная для «git push»:
  dev будет отправлена в dev (уже актуальна)
````