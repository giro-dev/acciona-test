# Acciona Twitter API - entrevista

## Instalacion 
Para ejecutar la aplicación antes se debe añadir las credenciales te twitter al fichero `src/main/resources/twitter4j.properties`
Una vez definidas las credenciales, ejecutar con una de las dos opciones disponibles:
* Opcion maven  
  Compilar con 
  ````shell
  mvn clean install 
  ````
  y ejecutar mediante 
  ````shell
  mvn spring-boot:run
  ````
* Opcion docker-compose   
  Compilar con
  ```shell
  docker-compose build
  ```
  y ejecutar
  ```shell
  docker-compose up -d
  ```

y acceder al swagger de la aplicación http://localhost:8080/swagger-ui.html


## Implementación
La api se ha desarrollado según las especificaciones detalladas y consta de dos ejes:
* Lectura de Tweets a través del Stream de la api de twitter.   
  Esta parte se encarga de nutrir la base de datos con los tweets que se van recuperando del stream mediante ``twitter4j``.
  La inclusión en la base de datos de los tweets que llegan esta sujeta a una serie de requisitos:
  * El idioma debe estar en la lista de permitidos (`twitter.accepted.languages`), si no existe se usará valores por defecto _es,it,fr_
  * Tener un mínimo de seguidores definido mediante (`twitter.minimum.followers`) y con valor por omision de _1500_
  Estos requisitos se pueden configurar en el fichero `src/main/resources/application.properties`
  
* API REST para consultar los tweets almacenados.

  * **PATCH**`/twitter-api/tweets/{id}`
  _Set validation status on selected tweet_  
  Permite marcar o desmarcar un tweet como válido segán el booleano que se pase como "valid".
  
  * **GET**
  `/twitter-api/tweets`
_Get all saved Tweets
user-controller_  
Lista todos los tweets almacenados.   
  
  * **GET**
`/twitter-api/users/{userId}/tweets`
_Get validated Tweets from user
hashtag-controller_   
Permite visualizar los tweets de un usuario definiendo si se quiere mostrar solo valinados o no validados mediante el parámetro `valid`.   
  * **GET**
`/twitter-api/hashtags`
_Get Top used Hastags_   
Muestra la lista de los hastags más utilizados ordenados descendentemente. Si no se detalla ningún valor como límite de la lista se toma 10 como valor por defecto.
