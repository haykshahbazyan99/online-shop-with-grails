<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Create Store</title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'application.css')}" type="text/css"/>
</head>
<body>

<div id="content">
  <h2>Create Store</h2>
  <g:form action="save">
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
      <label for="warehouse.id">Warehouse:</label>
      <g:select name="warehouse.id" from="${online.shop.Warehouse.list()}" optionKey="id" optionValue="name" value="${store?.warehouse?.id}" class="form-control" noSelection="['':'-Select Warehouse-']"/>
    </div>

    <fieldset class="buttons">
      <g:submitButton name="create" class="btn btn-primary" value="Create"/>
      <g:link class="btn btn-secondary" action="index" controller="store">Cancel</g:link>
    </fieldset>
  </g:form>
</div>

</body>
</html>
