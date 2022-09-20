<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
<div>
    <form>
        <a style="font-style:italic">
            Response message: ${sessionScope.message}
        </a>
    </form>
</div>

<div>
    <form id="get_all_products" method="GET" action="/">
        <input type="hidden" name="command" value="get_all_products"/>
        <button form="get_all_products" type="submit" style="font-size: 20">Show all products</button>
    </form>
</div>
<div class="main-block">
    <table cellspacing="16">
        <thead>
        <tr>
            <td><h3>product_id</h3></td>
            <td><h3>name</h3></td>
            <td><h3>description</h3></td>
            <td><h3>business_owner_id</h3></td>
            <td><h3>price</h3></td>
            <td><h3>delete_product</h3></td>
            <td><h3>info</h3></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.pageable.elements}" var="product">
            <tr>
                <td>${product.product_id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.business_owner_id}</td>
                <td>${product.price}</td>
                <td align="center">
                    <form action="/" method="POST">
                        <input name="command" type="hidden" value="delete_product"/>
                        <input name="product_id" type="hidden" value="${product.product_id}"/>
                        <button class="btn btn-primary" type="submit">Delete</button>
                    </form>
                </td>
                <td>
                    <form action="/" method="GET">
                        <input name="command" type="hidden" value="get_product_by_id"/>
                        <input name="product_id" type="hidden" value="${product.product_id}"/>
                        <button class="btn btn-primary" type="submit">Product Page</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="margin-left: center">
        <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">
            <c:if test="${i == pageable.pageNumber}">
                <button style="color:red" form="get_all_products" type="submit" name="currentPage" value="${i}">${i}</button>
            </c:if>
            <c:if test="${i != pageable.pageNumber}">
                <button form="get_all_products" type="submit" name="currentPage" value="${i}">${i}</button>
            </c:if>
        </c:forEach>
    </div>
</div>
<hr/>
<form action="/new_product">
    <button type="submit" style="font-size: 20">Create Product</button>
</form>
</body>
</html>