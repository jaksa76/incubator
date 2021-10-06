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
Now we have two pages. The second page displays a table of clients. This is because we used a different type of view. Notice that with the tabular view 
you can order entities and filter them using a simple query language (e.g. "age=32 AND salary>60000").

## Concepts

The application is the unit of deployment. The application comes with built in authentication, navigation menu, observability tools etc.

The application is composed of several pages.

A page can have one or more views.

Views display one or more instances of an entity.

Entities have fields, functions, operations. Fields can be primitive types or nested components. Functions are read-only
methods that return a value. Operations on the other hand have some side effects. Operations can have parameters. These 
parameters can be primitive types, complex types or enities.

You can also define relationships. Entities do not need to be aware of relationships they are involved in.


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

Entities and their fields can be annotated to restrict their visibility to the owner / any authenticated user / the whole internet. Same goes for modifyability. In this way you can define entities that can be modified only by their owner but are visible to all of the users.

In additioon to annotations, functions and operations can programmatically obtain the current user and present corresponding outcomes.

## Customization

The rendering of entities can be customized in several ways.

Different view types interpret the entities in their own way. A Tiles view will represent a grid of entities, where each entity will be a tile. A Tabular view will create a table with a row for each instance and a column for each field. You can define your own views, but that is an advanced topic.

Another way to customize the rendering of entities is through the annotation of fields. Fields can be annotated to be displayed using a different font/color/positioning.

Sometimes you want to display the same type of entity in two different ways. For this you can use decorators. Decorators are essentially subclasses of an entity that can hide some fields, override their annotations or define additional functions or operations.
