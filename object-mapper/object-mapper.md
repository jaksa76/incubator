# ORM with control

Object-mapper takes a ResultSet from a JDBC query and maps it to java objects.

```java
ObjectMapper om = new ObjectMapper(conn);
ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM EMPLOYEES WHERE ID = 123");
Employee employee = om.readOne(rs, Employee.class);
```

this allows fully flexible queries

```java
ResultSet rs = conn.createStatement().executeQuery(
                  "SELECT E.NAME, E.SURNAME, SUM(P.AMOUNT) "
                  + "FROM EMPLOYEES E JOIN PAYMENTS P ON P.TO = E.ID "
                  + "GROUP BY E.ID AND P.YEAR = '2018'");
Stream<Salary> s = om.readAll(rs, Salary.class);
```

Similarly it can update or insert rows in a table based on java objects.

```java
Table table = om.table(Employee.class);
table.insert(new Employee("John", "Doe"));
```

It supports a subset of the JPA annotations, excluding annotations for relationships. No lazy loading, no session detachment/attachment.

Relationships are managed explicitly and separately from the objects.

```java
om.connect(employee, department);
```

Object-mapper lets you manage transactions. True ACID, no session hacks.
