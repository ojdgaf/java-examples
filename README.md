# Java Examples

This repository contains 4 simple projects to try and meet some Java tools:
* **web app** - one page web application without any Java code to get to know `Maven` and `Tomcat` deployment process
* **hibernate app** - fetching entities from `PostgreSQL` database with `Hibernate` and writing them to JSON file via `Jackson`
* **spring app** - XML-based `Spring` application which supports CRUD functionality using `Servlet` and `JSP`
* **boot app** - `Spring Boot` REST API service protected by `JWT`

### Where to begin?
```bash
git clone git@github.com:ojdgaf/java-examples.git
cd java-examples
scripts/build.sh
```
### Containers
The script above creates 4 `Docker` containers within "java-examples" network
* **tom** - `Tomcat` deployment server (port 8080)

Visit `http://localhost:8080/webapp` and `http://localhost:8080/springapp` to test `webapp` and `springapp` respectively

* **mav** - `Maven` instance (port 8081)

`hibernateapp` creates JSON file after execution - use `cat examples/hibernateapp/log.json`

`bootapp` is running in the background and available at `http://localhost:8081`

* **pos** - `PostgreSQL` database (port 8082)
* **adm** - `Adminer` instance (port 8083)

### More about Spring Boot Application

see logs using `cat examples/bootapp/run.log`

import Postman [collection](https://www.getpostman.com/collections/21879dfb8258d500d55e) and check out some endpoints

stop the container to stop the background execution

it supports live reload. in order to use this feature you have to [create](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html#_running_the_remote_client_application) remote configuration in your IDE as well as [enable](https://dzone.com/articles/spring-boot-application-live-reload-hot-swap-with) autobuild and automake. although it's so inconvenient i'd rather suggest you to develop locally
