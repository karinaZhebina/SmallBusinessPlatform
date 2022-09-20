<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>Create Product</title>
</head>
<body>
<div>
    <h1>Create Product</h1>
    <hr/>
    <a href="/">Go to Home</a>
    <hr/>
</div>
<div class="main-block">
        <div class="main-block">
            <div>
                <form>
                    <h2>
                        New Product
                    </h2>
                </form>
            </div>

            <form class="form-group" id="create_product" action="/" method="POST">
                <input type="hidden" name="command" value="create_product"/>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="product_id" placeholder="${product_id}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="name" placeholder="${name}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="description" placeholder="${description}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="business_owner_id" placeholder="${business_owner_id}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="price" placeholder="${price}"/>
                </div>
            </form>
        </div>
        <button class="btn btn-primary" form="create_product">Submit</button>
</div>
</body>
</html>