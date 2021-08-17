# Developers Challenge Back v2

_Implementación de un Microservicio que consume Tweets y basado en unos criterios
de configuración los persista en una base de datos para su gestión a través de una API REST._

## Comenzando 🚀

_Para este proyecto necesitamos tener instalados un entorno de desarrollo 
como IntelliJ y la herramienta de despliegue de contenedores Docker. Ambos se pueden
descargar de su página oficial._


## Instalación y ejecución 🔧

_Para ejecutar el proyecto, lo primero que tenemos que hacer es lanzar el archivo docker-compose para montar
en Docker dos contenedores, uno para la base de datos mongodb y otro para la interfaz
gráfica de la base de datos utilizando mongo-express._

_El comando sería el siguiente:_

```
docker-compose up --build
```

_Una vez hemos desplegado los contenedores, asignamos los tokens de acceso de Twitter
en el archivo "twitter4j.properties", el cual se encuentra en el paquete "resources", junto con el tamaño de la lista de clasificación de hashtags
(que por defecto se encuentra a diez). Con esto, ya podemos ejecutar el programa desde 
IntelliJ y realizar las peticiones mediante los endpoints asignados._

## Documentación 📖️

_Para documentar la API hemos utilizado la herramienta Swagger, a la cual podemos acceder 
mediante el siguiente enlace:_

```
http://localhost:8080/swagger-ui/index.html
```

## Ejecutando las pruebas ⚙️

_Se han realizado test para comprobar el correcto funcionamiento del controller y del service de
nuestra API REST mediante la herramienta de SpringBoot Test._


## Construido con 🛠️

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - Entorno de desarrollo
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Docker](https://www.docker.com/) - Despliegue de la base de datos en contenedores
* [Spring](https://spring.io/) - Framework de desarrollo de aplicaciones 
* [Java 11](https://www.oracle.com/es/java/technologies/javase-jdk11-downloads.html) - Lenguaje de programación usado


## Autores ✒️

* **Andrés Miras Gonzalez** - [amiras1](https://gitlab.com/amiras1)
