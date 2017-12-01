<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">

        <div class="panel panel-default">
            <div class="panel-body">
                <form action="" class="form-inline">
                    <input type="text" placeholder="商品名称" name="q_like_s_productName" value="${param.q_like_s_productName}" class="form-control">
                    <input type="text" placeholder="价格 或 市场价格" name="q_eq_bd_price_or_marketPrice" value="${param.q_eq_bd_price_or_marketPrice}" class="form-control">
                    <button class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>


        <a href="/product/new" class="btn btn-success">添加</a>
        <table class="table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>市场价</th>
                <th>产地</th>
                <th>评论数量</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.items}" var="product">
                    <tr>
                        <td><a href="/product/${product.id}">${product.productName}</a> </td>
                        <td>${product.price}</td>
                        <td>${product.marketPrice}</td>
                        <td>${product.place}</td>
                        <td>${product.commentNum}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>