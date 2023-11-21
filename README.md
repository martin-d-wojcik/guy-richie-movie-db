# Guy Ritchie Movies     

A Spring Boot application with an PostgreSQL database soon to be containerized.
The database consists of two tables, movies and actors. 
---
## Installation       

Install manually by... TODO   
Install by running the app as a docker container. Not yet implemented.
---
## Usage of movies  
#### Read all movies    
Request: *GET*   
Endpoint: */api/v1/movies*   
Response body:
```
[
  {
    "id": Long,
    "title": String,
    "writer": String,
    "director": String,
    "release_year": int,
    "genre": String,
    "awards": String
  }
]
```
Status code: 200 OK
#### Get a movie by id
Request: *GET*   
Endpoint: */api/v1/movie/id/{id}*    
Response body:
```
{
  "id": Long,
  "title": String,
  "writer": String,
  "director": String,
  "release_year": int,
  "genre": String,
  "awards": String
}
```    
Status code: 200 OK

#### Get movies by release year
Request: *GET*   
Endpoint: */api/v1/movie/year/{year}*
Response body:
```
[
  {
    "id": Long,
    "title": String,
    "writer": String,
    "director": String,
    "release_year": int,
    "genre": String,
    "awards": String
  }
]
```   
Status code: 200 OK
#### Add new movie    
Endpoint: */api/v1/movie/id/{id}*    
Request: *POST*    
Request body:     
```
{
  "id": Long,
  "title": String,
  "writer": String,
  "director": String,
  "release_year": int,
  "genre": String,
  "awards": String
}
```  

Response body:
```
{
  "Created The man from U.N.C.L.E"
}
```
Status code: 201 - Created     
#### Update movie
Endpoint: */api/v1/movie/{id}*    
Request: *PUT*    
Request body:
```
{
  "title": String,
  "writer": String,
  "director": String,
  "release_year": int,
  "genre": String,
  "awards": String
}
```  

Response body:
```
{
  "Updated The man from U.N.C.L.E"
}
```
Status code: 200 - OK     
#### Delete movie
Endpoint: */api/v1/movie/{id}*    
Request: *DELETE*        
Response body:
```
{
  "Deleted The man from U.N.C.L.E"
}
```      

Status code: 200 - OK

---         
## Usage of actors
#### Read all actors
Request: *GET*   
Endpoint: */api/v1/actors*   
Response body:
```
[
  {
    "id": Long,
    "firstName": String,
    "lastName": String,
    "birthCountry": String,
    "movieId": Long
  }
]
```
Status code: 200 OK
#### Get a actor by id
Request: *GET*   
Endpoint: */api/v1/actor/id/{id}*    
Response body:
```
{
  "id": Long,
  "firstName": String,
  "lastName": String,
  "birthCountry": String,
  "movieId": Long
}
```    
Status code: 200 OK

#### Get a actor by name
Request: *GET*   
Endpoint: */api/v1/actor/id/{name}*    
Response body:
```
{
  "id": Long,
  "firstName": String,
  "lastName": String,
  "birthCountry": String,
  "movieId": Long
}
```    
Status code: 200 OK
#### Add new actor
Endpoint: */api/v1/actor*    
Request: *POST*    
Request body:
```
{
  "id": Long,
  "firstName": String,
  "lastName": String,
  "birthCountry": String,
  "movieId": Long
}
```  

Response body:
```
{
  "Created Jeff Bridges"
}
```
Status code: 201 - Created
#### Update actor
Endpoint: */api/v1/actor/{id}*    
Request: *PUT*    
Request body:
```
{
  "firstName": String,
  "lastName": String,
  "birthCountry": String,
  "movieId": Long
}
```  

Response body:
```
{
  "Updated Lester"
}
```
Status code: 200 - OK
#### Delete movie
Endpoint: */api/v1/actor/{id}*    
Request: *DELETE*        
Response body:
```
{
  "Deleted Jeff Bridges"
}
```      

Status code: 200 - OK      
#### Read actors by movie id
Request: *GET*   
Endpoint: */api/v1/movie/{movieId}/actors*   
Response body:
```

{
  "Lock, Stock and Two Smoking Barrels": 
    [
      {
        "firstName": Vinnie",
        "lastName": "Jones"
      },
      {
        "firstName": "Jason",
        "lastname": "Statham"
      }
    ]
}
```
Status code: 200 OK

---