# Developers Challenge Back v2

Implementación de un Microservicio que consume Tweets y basado en unos criterios
de configuración los persista en una base de datos para su gestión a través de una API REST.

## Comenzando 🚀

Para este proyecto necesitamos tener instalados un entorno de desarrollo 
como IntelliJ y la herramienta de despliegue de contenedores Docker. Ambos se pueden
descargar de su página oficial.


## Instalación y ejecución 🔧

Para ejecutar el proyecto, lo primero que tenemos que hacer es ubicarnos en la carpeta raíz del proyecto y lanzar el archivo docker-compose. 
Este comando montará para montar dos contenedores, uno para la base de datos mongodb y otro para la interfaz
gráfica de la base de datos, que en este caso será mongo-express.

El comando sería el siguiente:

```
docker-compose up --build
```

Una vez hemos desplegado los contenedores, asignamos los tokens de acceso de Twitter
en el archivo "twitter4j.properties", el cual se encuentra en el paquete "resources", junto con el tamaño de la lista de clasificación de hashtags
(que por defecto se encuentra a diez). Con esto, ya podemos ejecutar el programa desde 
IntelliJ y realizar las peticiones mediante los endpoints asignados.

Para dar un ejemplo de acceso a los endpoints, en Postman o en la url de nuestro navegador introducimos la 
siguiente dirección:
```
http://localhost:8080/tweets/getTweets
```

Para poder observar los distintos valores que se almacenan en la base de datos, en la url de nuestro navegador
utilizaremos la siguiente dirección para acceder a mongo-express:
```
http://localhost:8081
```

Aquí podremos observar como se nos ha creado una base de datos llamada TwitterDatabase donde se nos almacenarán tanto los tweets
como los hashtags.
## Documentación 📖️

Para documentar la API se ha utilizado la herramienta Swagger, a la cual podemos acceder 
mediante el siguiente enlace:

```
http://localhost:8080/swagger-ui/index.html
```

## Ejecutando las pruebas ⚙️

Se han realizado test para comprobar el correcto funcionamiento del controller y del service de
nuestra API REST mediante la herramienta de SpringBoot Test.


## Construido con 🛠️

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - Entorno de desarrollo
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Docker](https://www.docker.com/) - Despliegue de la base de datos en contenedores
* [Spring](https://spring.io/) - Framework de desarrollo de aplicaciones 
* [Java 11](https://www.oracle.com/es/java/technologies/javase-jdk11-downloads.html) - Lenguaje de programación usado


## Autores ✒️

* **Andrés Miras Gonzalez** - Gitlab: [amiras1](https://gitlab.com/amiras1) | Github: [amg813](https://github.com/amg813)
