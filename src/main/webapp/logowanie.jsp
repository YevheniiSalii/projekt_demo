<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 23.08.2020
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <titl>Projekt Demo</titl>
    </title>
</head>
<body>
<h2>logowanie</h2>


<FORM action="/projekt_demo/logowanie" METHOD="POST">
    Imie:
    <input type="text" name="nickname"><br>
    Haslo;
    <input type="text" name="password"><br>
    <input type="submit" value="wysli">

</FORM>
<a href="/projekt_demo/index.jsp">zalogoj sie</a>

</body>
</html>
