<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Products</title>
</head>
<body>
<a href="/new_product" style="font-size: 24">Create Product</a>
<hr/>
<br>
<div>
    <form id="get_all_products" method="GET" action="/">
        <input type="hidden" name="command" value="get_all_products"/>
        <button form="get_all_products" type="submit" style="font-size: 20">Show all products</button>
    </form>
</div>
<div class="main-block">
    <table>
        <thead>
        <tr>
            <td>
                <h4>
                    <c:out value="product_id"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="name"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="description"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="business_owner_id"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="price"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="delete_product"/>
                </h4>
            </td>
            <td>
                <h4>
                    <c:out value="info"/>
                </h4>
            </td>
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
                <td>
                    <form action="frontController" method="DELETE">
                        <input name="command" type="hidden" value="delete_product"/>
                        <input name="product_id" type="hidden" value="${product.product_id}"/>
                        <button class="btn btn-primary" type="submit">Delete</button>
                        <br/>
                    </form>
                </td>
                <td>
                    <form action="frontController" method="GET">
                        <input name="command" type="hidden" value="get_product_by_id"/>
                        <input name="product_id" type="hidden" value="${product.product_id}"/>
                        <button class="btn btn-primary" type="submit">Product Page</button>
                        <br/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="margin-left: center">
        <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">
            <c:if test="${i == pageable.pageNumber}">
                            <span>
                                <button style="color:red" form="get_all_products" type="submit" name="currentPage"
                                        value="${i}">${i}</button>
                            </span>
            </c:if>
            <c:if test="${i != pageable.pageNumber}">
                            <span>
                                <button form="get_all_products" type="submit" name="currentPage" value="${i}">${i}</button>
                            </span>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>