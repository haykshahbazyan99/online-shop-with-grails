<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Show Store</title>
</head>
<body>
<h1>Store Details</h1>
<p><strong>Code:</strong> ${store.code}</p>
<p><strong>Name:</strong> ${store.name}</p>
<p><strong>Address:</strong> ${store.address ?: 'N/A'}</p>

<!-- Sell product form -->
<g:form controller="store" action="sellProduct">
  <h2>Sell Product</h2>
  <label for="productId">Product:</label>
  <g:select id="productId" name="productId"
            from="${store.products.collect { it.product }}"
            optionKey="id" optionValue="name" required="true"/>
  <label for="quantity">Quantity:</label>
  <g:textField id="quantity" name="quantity" required="true"/>
  <g:hiddenField name="storeId" value="${store.id}"/>
  <g:submitButton name="Sell" value="Sell"/>
</g:form>

<!-- Return expired products form -->
<g:form controller="store" action="returnExpiredProducts">
  <h2>Return Expired Products</h2>
  <g:hiddenField name="storeId" value="${store.id}"/>
  <g:submitButton name="Return Expired Products" value="Return Expired Products"/>
</g:form>

<!-- Return product to warehouse form -->
<g:form controller="store" action="returnProduct">
  <h2>Return Product to Warehouse</h2>
  <g:hiddenField name="storeId" value="${store.id}"/>
  <label for="productId">Product:</label>
  <g:select id="productId" name="productId"
            from="${store.products.collect { it.product }}"
            optionKey="id" optionValue="name" required="true"/>
  <label for="quantity">Quantity:</label>
  <g:textField id="quantity" name="quantity" required="true"/>
  <g:submitButton name="Return" value="Return"/>
</g:form>

<g:link action="index">Back to Stores</g:link>

</body>
</html>
