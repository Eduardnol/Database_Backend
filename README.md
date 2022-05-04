# REST API Database User Administration




## When cloning 
Create inside /src/main/resources a file names `application.properties` with the following info inside.
```properties
spring.data.mongodb.authentication-database=xxxx
#spring.data.mongodb.username=xxxx
#spring.data.mongodb.password=xxxx
#spring.data.mongodb.database=xxxx
#spring.data.mongodb.port=xxxx
#spring.data.mongodb.host=xxxx
spring.data.mongodb.uri=xxxx
spring.data.mongodb.auto-index-creation=true
spring.servlet.multipart.max-file-size=5000KB
spring.servlet.multipart.max-request-size=5000KB
## Hibernate properties
spring.jpa.hibernate.use-new-id-generator-mappings=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.open-in-view=false
```



This is a REST API that allows you to create, update, delete and list all the users included in a
database.

The aim is to help a NGO organisation to manage all the users included in there. They can be classified into different
groups
and can be assigned to different roles.

To store all the data, a MongoDB database is used. And in order to get better search results and faster ones,
Meilisearch is used. The API itself has been developed using Java and Spring Boot.

> In order to start the API, you need `docker` and `docker-compose` installed. Since the Database and the Search Engine
> are
> executed from a container.

## Frontend

The frontend is developed in Vue3 and Boostrap, it can be reviewed in the following repo:
[https://github.com/Eduardnol/Database-Front](https://github.com/Eduardnol/Database-Front)