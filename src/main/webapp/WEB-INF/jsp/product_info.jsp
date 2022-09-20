<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Product Info</title>
</head>
<body>
<h1>Product Info Page</h1>
<div>
    <hr/>
    <a href="/" style="font-size: 20">Go to Home</a>
    <hr/>
</div>

<!----------   SHOW INFO  ---------->
<c:if test="${not empty requestScope.product}">
    <div>
        <table cellspacing="16">
            <thead>
            <tr>
                <td><h3>product_id</h3></td>
                <td><h3>name</h3></td>
                <td><h3>description</h3></td>
                <td><h3>business_owner_id</h3></td>
                <td><h3>price</h3></td>
                <td><h3>delete</h3></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.product.product_id}</td>
                <td>${requestScope.product.name}</td>
                <td>${requestScope.product.description}</td>
                <td>${requestScope.product.business_owner_id}</td>
                <td>${requestScope.product.price}</td>
                <td>
                    <form action="/product_info" method="POST">
                        <input name="command" type="hidden" value="delete_product"/>
                        <input name="product_id" type="hidden" value="${product.product_id}"/>
                        <button class="btn btn-primary" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <!----------  UPDATE  ---------->
    <div class="main-block">
        <form action="/" class="form-group" id="update_product" method="POST">
            <input name="command" type="hidden" value="update_product"/>
            <input name="product_id" type="hidden" value="${product.product_id}"/>
            <div>
                <input name="name" placeholder="name${name}" type="text" style="font-size:18" size="40"/>
            </div>
            <div>
                <input name="description" placeholder="description${description}" type="text" style="font-size:18" size="40"/>
            </div>
            <div>
                <input name="business_owner_id" placeholder="business_owner_id${business_owner_id}" type="text" style="font-size:18" size="40"/>
            </div>
            <div>
                <input name="price" placeholder="price${price}" type="text" style="font-size:18" size="40"/>
            </div>
        </form>
        <button class="btn btn-primary" form="update_product" type="submit" style="font-size:16">Update Product</button>
    </div>
</c:if>
</body>
</html>
