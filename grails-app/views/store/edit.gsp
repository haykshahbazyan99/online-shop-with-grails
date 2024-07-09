<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <title>Edit Store</title>
</head>
<body>
<h1>Edit Store</h1>
<g:form action="update">
  <div class="fieldcontain">
    <label for="name">Name:</label>
    <g:textField name="name" value="${store?.name}"/>
  </div>

  <div class="fieldcontain">
    <label for="code">Code:</label>
    <g:textField name="code" value="${store?.code}"/>
  </div>

  <div class="fieldcontain">
    <label for="address">Address:</label>
    <g:textField name="address" value="${store?.address}"/>
  </div>

  <div class="fieldcontain">
    <label for="warehouseId">Warehouse:</label>
    <g:select name="warehouseId" from="${Warehouse.list()}" optionKey="id" optionValue="name"
              value="${store?.warehouse?.id}" />
  </div>

  <fieldset class="buttons">
    <g:submitButton name="update" class="btn btn-primary" value="Update"/>
    <g:link class="btn btn-secondary" action="index" controller="store">Cancel</g:link>
  </fieldset>
</g:form>
</body>
</html>