# ORM with control

Object-mapper takes a ResultSet from a JDBC query and maps it to java objects.

```java
ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM EMPLOYEES WHERE ID = 123");
Employee employee = om.readOne(rs, Employee.class);
```

this allows more flexibility with queries


Similarly it can update or insert rows in a table based on java objects.

```java
Table table = om.table(Employee.class);
table.insert(new Employee("John", "Doe"));
```

It supports a subset of the JPA annotations, excluding annotations for relationships.

Relationships are managed explicitly and separately from the objects.

```java
om.connect(employee, department);
```
