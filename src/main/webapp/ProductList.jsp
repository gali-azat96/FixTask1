<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>TASK 1</title>
</head>
<body>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of products</h2></caption>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
    </tr>
    <c:forEach var="product" items="${listProduct}">
      <tr>
        <td><c:out value="${product.id}" /></td>
        <td><c:out value="${product.name}" /></td>
        <td><c:out value="${product.price}" /></td>
      </tr>
    </c:forEach>
  </table>
</div>

<div style="text-align: center;">
  <h2>
    <a href="/new">Add product</a>

  </h2>
</div>
</body>
</html>