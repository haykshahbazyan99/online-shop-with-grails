<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Edit Warehouse</title>
</head>
<body>
<h1>Edit Warehouse</h1>
<g:form controller="warehouse" action="update">
  <fieldset>
    <g:hiddenField name="id" value="${warehouse.id}"/>
    <div>
      <label for="code">Code</label>
      <g:textField name="code" value="${warehouse.code}"/>
    </div>
    <div>
      <label for="name">Name</label>
      <g:textField name="name" value="${warehouse.name}"/>
    </div>
  </fieldset>
  <g:submitButton name="update" value="Update"/>
</g:form>
<g:link action="index">Back to List</g:link>
</body>
</html>