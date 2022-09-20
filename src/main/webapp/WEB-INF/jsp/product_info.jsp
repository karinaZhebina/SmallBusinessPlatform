<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>
        Product Info
    </title>
</head>
<body>
<h2>Product Info Page!</h2>
<div>
    <hr/>
    <a href="/">Go to Home</a>
    <hr/>
</div>

<!----------   SHOW INFO  ---------->
<div>
    <form>
        <a>
            ${sessionScope.message}
        </a>
    </form>
</div>
<c:if test="${not empty requestScope.product}">
    <div>
        <table class="table col-12">
            <thead>
            <tr>
                <td>
                    <h4>product_id</h4>
                </td>
                <td>
                    <h4>name</h4>
                </td>
                <td>
                    <h4>description</h4>
                </td>
                <td>
                    <h4>business_owner_id</h4>
                </td>
                <td>
                    <h4>price</h4>
                </td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.product.product_id}</td>
                <td>${requestScope.product.name}</td>
                <td>${requestScope.product.description}</td>
                <td>${requestScope.product.business_owner_id}</td>
                <td>${requestScope.product.price}</td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>

</br>
    <!----------   UPDATE (ADMIN)  ---------->
    <div class="main-block">
        <form action="/product_info" class="form-group" id="update_product" method="PUT">
            <input name="command" type="hidden" value="update_product"/>
            <div class="form-group col-md-6">
                <input class="form-control" name="product_id" placeholder="${product_id}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="name" placeholder="${name}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="description" placeholder="${description}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="business_owner_id" placeholder="${business_owner_id}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="price" placeholder="${price}" type="text"/>
            </div>
            <div>
                <button class="btn btn-primary" form="update_product" type="submit">Update Product</button>
            </div>
        </form>
    </div>
</body>
</html>
