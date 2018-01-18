# Torres SpringBoot Project templates
- https://torreswoo.github.io/posts/springboot-jolokia/

## Start project
- build, run
```
$ ./gradlew build
$ ./gradlew bootrun
```
## dashboard
```
- http://localhost:8080/          # Swagger
- http://localhost:8080/intro     # intro page (Thymeleaf)
- http://localhost:8080/dashboard # App Dashboard
- http://localhost:8080/console   # H2 web console
```
![springboot-jolokia-dashboard](/docs/images/dashboard01.png)

## Configuration files
| path file | info |
| --- | --- |
| .gitignore | --- |
| build.gradle | Gradle build 파일 |
| /gradle/querydsl.gradle | Gradle QueryDSL 설정파일 |
| /public/* | web static resource : HTML, CSS, javascript |
| /config/application.yml | profile, DB설정파일 |
| /src/main/generated/* | Q파일 |
| /src/main/resources/application.properties |  |
| /src/main/resources/logback.groovy | logging 설정 - Groovy |
| /src/main/resources/logback.xml | logging 설정 - XML |
| /src/main/resources/ data.sql, schema.sql, import.sql | datasource initial SQL |
| /src/main/resources/templates/ | Template engine view - Thymeleaf |


---
## Library
| name | version  | download link |
| --- | --- | --- |
| SpringBoot | v1.5.9 | https://projects.spring.io/spring-boot/ |
| Lombok | v1.16.8 | https://projectlombok.org/ |
| jQuery | v1.12.1 | http://jquery.com | 
| jQuery UI | v1.12.1 | https://jqueryui.com |
| Bootstrap | v3.3.7 | https://getbootstrap.com/docs/3.3/ |
| Font Awesome | v4.7.0 | http://fontawesome.io/ |
| Highcharts | v6.0.4 (2017-12-15) | https://www.highcharts.com/download |
| jolokia | v1.3.7 | https://jolokia.org/download.html |
| springfox | v2.6.0 | http://springfox.github.io/springfox/ |
| H2 | v1.4.190 | http://www.h2database.com/ |
| Spring Data JPA | 1.11.9 | https://projects.spring.io/spring-data-jpa |
| QueryDSL | v4.1.4 | http://www.querydsl.com/ |


---
## Details

### SpringBoot | Actuator
```
- http://localhost:8080/docs
- http://localhost:8080/mappings
```
### SpringBoot | Admin
```
- http://localhost:8080
```
### SpringBoot | remote shell
```
$ ssh user@localhost -p 2000 
$ dashboard 
$ metrics
$ thread top
```
### jolokia
- https://jolokia.org/reference/html/index.html

### logging
- logback

### Swagger & springfox
- springfox :http://springfox.github.io/springfox/

### Datasource
- H2
- MySQL

### JPA & QueryDSL
- SpringData JPA
- QueryDSL


