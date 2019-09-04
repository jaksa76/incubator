# OOPS

Object Oriented Programming reborn

Just create your domain objects and publish them.

```java
OOPS.publishEntity(Employee.class);
```

OOPS will create a REST endpoint for your entities with:

all the usual operations
```
GET /employee

GET /employee/123

POST /employee
{ 'name':'John', 'surname':'Doe' }

PUT /employee/123
{ 'name':'Johnathan', 'surname':'Doe' }

DELETE /employee/345
```

queries
```
GET /employee?name=John&age=42
```

methods
```
POST /employee/123/sendEmail
{ 'subject':'happy new year', 'content':'Greetings from management!' }
```

Entities and their fields can be annotated to restrict their visibility to the owner / any authenticated user / the whole internet.

OOPS will also generate a basic UI for managing objects.

The rendering of entities can be customized.
