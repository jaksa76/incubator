# OOPS

Object Oriented Programming reborn

## Quickstart

Just create your domain objects and publish them:

```java
Application app = new Application();
app.addPage(Tiled.viewOf(Employee.class));
app.run();
```
Open http://localhost:8080 and you should see a web page that allows you to add, modify and delete employees. The employees are displayed as tiles.
This code is enough to generate the UI, the REST API, the database schema and all the CRUD operations.
Let's add another entity:

```java
Application app = new Application("My Company");
app.addPage(Tiled.viewOf(Employee.class));
app.addPage(Tabular.viewOf(Client.class));
app.run();
```
Now we have two pages. The second page displays a table of clients. This is because we used a different type of view.

## Concepts

The application is the unit of deployment. The application comes with built in authentication, navigation menu, observability tools etc.

The application is composed of several pages.

A page can have one or more views.

Views display one or more instances of an entity.


## Infrastructure

Not only has the UI been generated. A REST endpoint will be available for your entities with

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

## Scope of Entities

Entities and their fields can be annotated to restrict their visibility to the owner / any authenticated user / the whole internet.

## Customization

The rendering of entities can be customized.
