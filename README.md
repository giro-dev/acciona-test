# Acciona Twitter API - entrevista

## Instalacion 
Para ejecutar la aplicacion 

## Implementación
La api se ha desarrollado segun las especificaciones detalladas y consta de dos ejes:
* Lectura de Tweets a través del Stream de la api de twitter.   
  Esta parte se encarga de nutrir la base de datos con los tweets que se van recuperando del stream mediante ``twitter4j``.
  La inclusión en la base de datos de los tweets que llegan esta sujeta a una serie de requisitos:
  * ser del idioma de la lista de permitidos 
  * tener un mínimo de seguidores
    
  
* API REST para consultar los tweets almacenados.

tweet-controller


**PATCH**
`/twitter-api/tweets/{id}`
_Set validation status on selected tweet_  
Permite marcar o desmarcar un tweet como valido segun el booleano que se pase como "valid".


**GET**
`/twitter-api/tweets`
_Get all saved Tweets
user-controller_


**GET**
`/twitter-api/users/{userId}/validatedTweets`
_Get validated Tweets from user
hashtag-controller_


**GET**
`/twitter-api/hashtags`
_Get Top used Hastags_