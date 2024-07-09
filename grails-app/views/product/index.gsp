<%--
  Created by IntelliJ IDEA.
  User: hayks
  Date: 08-Jul-24
  Time: 4:36 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="g" uri="http://grails.org/tags" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'application.css')}" type="text/css"/>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        color: #333;
    }

    #header {
        background-color: #007BFF;
        color: #fff;
        padding: 20px;
        text-align: center;
    }

    #content {
        padding: 20px;
        margin: 20px auto;
        background: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 80%;
    }

    h2 {
        border-bottom: 2px solid #007BFF;
        padding-bottom: 10px;
        color: #007BFF;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        padding: 10px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    .action-links a {
        text-decoration: none;
        color: #007BFF;
        padding: 5px 10px;
        border-radius: 5px;
        transition: background 0.3s ease;
    }

    .action-links a:hover {
        background: #007BFF;
        color: #fff;
    }

    #footer {
        background-color: #333;
        color: #fff;
        text-align: center;
        padding: 10px;
        position: fixed;
        bottom: 0;
        width: 100%;
    }
    </style>
</head>

<body>
<div id="header">
    <g:link uri="/">
        <h1>Back to Home</h1>
    </g:link>
</div>

<div id="content">
    <h2>Available Products</h2>
    <table>
        <thead>
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Price</th>
            <th>Production Date</th>
            <th>Deadline</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${productList}" var="product">
            <tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.productionDate}</td>
                <td>${product.deadline}</td>
                <td class="action-links">
                    <g:link controller="product" action="edit" id="${product.id}">Edit</g:link>
                    <g:link controller="product" action="show" id="${product.id}">Show</g:link>
                    <form method="post" action="${g.createLink(controller: 'product', action: 'delete', id: product.id)}" style="display:inline;" onsubmit="return confirm('Are you sure?');">
                        <g:hiddenField name="_method" value="DELETE"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

<div id="footer">
    <p>&copy; 2024 Online Shop. All rights reserved.</p>
</div>
</body>
</html>