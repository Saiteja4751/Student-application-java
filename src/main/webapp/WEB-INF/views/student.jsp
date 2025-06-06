<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        input, button { margin: 5px; padding: 8px; }
        table, th, td { border: 1px solid black; padding: 8px; border-collapse: collapse; }
    </style>
</head>
<body>

<h1>Student Management</h1>

<form:form method="POST" action="/students/save" modelAttribute="student">
    <form:hidden path="id"/>
    <input type="text" name="name" placeholder="Name" value="${student.name}" required />
    <input type="email" name="email" placeholder="Email" value="${student.email}" required />
    <input type="text" name="phone" placeholder="Phone" value="${student.phone}" required />
    <input type="text" name="grade" placeholder="Grade" value="${student.grade}" required />
    <button type="submit">${student.id == null ? 'Add' : 'Update'}</button>
</form:form>

<h2>Student List</h2>
<table>
    <thead>
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Grade</th><th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="stu" items="${students}">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.email}</td>
            <td>${stu.phone}</td>
            <td>${stu.grade}</td>
            <td>
                <a href="/students/edit/${stu.id}">Edit</a> |
                <a href="/students/delete/${stu.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
