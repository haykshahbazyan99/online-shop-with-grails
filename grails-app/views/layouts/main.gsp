<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:layoutHead/>
    <title><g:layoutTitle default="Grails"/></title>
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

    ul {
        list-style: none;
        padding: 0;
    }

    ul li {
        margin: 10px 0;
    }

    ul li a {
        text-decoration: none;
        color: #007BFF;
        background: #f9f9f9;
        padding: 10px 20px;
        display: block;
        border-radius: 5px;
        transition: background 0.3s ease;
    }

    ul li a:hover {
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
<!-- Custom header (if needed) -->
<div id="header">
    <h1>Online Shop</h1>
</div>

<!-- Main content area -->
<div id="content">
    <g:layoutBody/>
</div>

<!-- Navigation Links -->
<div id="navigation">
    <h2>Navigation</h2>
    <ul>
        <li><g:link controller="product" action="index">List Products</g:link></li>
        <li><g:link controller="product" action="create">Create Product</g:link></li>
%{--        <li><g:link controller="product" action="checkExpiredProducts">Check Expired Products</g:link></li>--}%
        <li><g:link controller="warehouse" action="index">List Warehouses</g:link></li>
        <li><g:link controller="warehouse" action="create">Create Warehouse</g:link></li>
        <li><g:link controller="store" action="index">List Stores</g:link></li>
        <li><g:link controller="store" action="create">Create Store</g:link></li>

    </ul>
</div>

<div id="footer">
    <p>&copy; 2024 Online Shop. All rights reserved.</p>
</div>
</body>
</html>