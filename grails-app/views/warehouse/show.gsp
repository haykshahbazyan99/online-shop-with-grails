<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Show Warehouse</title>
</head>
<body>
<h1>Warehouse Details</h1>
<p><strong>Code:</strong> ${warehouse.code}</p>
<p><strong>Name:</strong> ${warehouse.name}</p>

<!-- Form to add product to warehouse -->
<g:form controller="warehouse" action="addProduct">
  <h2>Add Product</h2>
  <label for="productId">Product:</label>
  <g:select id="productId" name="productId"
            from="${online.shop.Product.list()}"
            optionKey="id" optionValue="name" required="true"/>
  <label for="amount">Amount:</label>
  <g:textField id="amount" name="amount" required="true"/>
  <g:hiddenField name="warehouseId" value="${warehouse.id}"/>
  <g:submitButton name="Add" value="add"/>
</g:form>

<!-- Form to remove product from warehouse -->
<g:form controller="warehouse" action="removeProduct">
  <h2>Remove Product</h2>
  <label for="productId">Product:</label>
  <g:select id="productId" name="productId"
            from="${warehouse.products.collect { it.product }}"
            optionKey="id" optionValue="name" required="true"/>
  <g:hiddenField name="warehouseId" value="${warehouse.id}"/>
  <g:submitButton name="Remove" value="remove"/>
</g:form>

<!-- Form to deliver product to store -->
<g:form controller="warehouse" action="deliverProduct">
  <h2>Deliver Product to Store</h2>
  <label for="productId">Product:</label>
  <g:select id="productId" name="productId"
            from="${warehouse.products.collect { it.product }}"
            optionKey="id" optionValue="name" required="true"/>
  <label for="storeId">Store:</label>
  <g:select id="storeId" name="storeId"
            from="${online.shop.Store.list()}"
            optionKey="id" optionValue="name" required="true"/>
  <label for="amount">Amount:</label>
  <g:textField id="amount" name="amount" required="true"/>
  <g:hiddenField name="warehouseId" value="${warehouse.id}"/>
  <g:submitButton name="Deliver" value="deliver"/>
</g:form>

<g:link action="index">Back to Warehouses</g:link>

</body>
</html>
