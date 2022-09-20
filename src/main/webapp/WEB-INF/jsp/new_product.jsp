<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Create Product</title>
</head>
<body>
<h1>Create Product</h1>
<div>
    <hr/>
    <a href="/" style="font-size: 20">Go to Home</a>
    <hr/>
</div>

<div class="main-block">
    <div class="main-block">
        <div>
                <h2>New Product</h2>
        </div>

        <form action="/" class="form-group" id="create_product"  method="POST">
            <input type="hidden" name="command" value="create_product"/>
            <div>
                <input class="form-control" type="text" name="product_id" placeholder="product_id${product_id}" style="font-size: 18" size="40"/>
            </div>
            <div>
                <input class="form-control" type="text" name="name" placeholder="name${name}" style="font-size: 18" size="40"/>
            </div>
            <div>
                <input class="form-control" type="text" name="description" placeholder="description${description}" style="font-size: 18" size="40"/>
            </div>
            <div>
                <input class="form-control" type="text" name="business_owner_id" placeholder="business_owner_id${business_owner_id}" style="font-size: 18" size="40"/>
            </div>
            <div>
                <input class="form-control" type="text" name="price" placeholder="price${price}" style="font-size: 18" size="40"/>
            </div>
        </form>
    </div>
    <button class="btn btn-primary" form="create_product" style="font-size:16">Submit</button>
</div>
</body>
</html>