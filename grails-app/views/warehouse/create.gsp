<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Create Warehouse</title>
</head>
<body>
<h1>Create Warehouse</h1>
<g:form controller="warehouse" action="save">
  <fieldset>
    <div>
      <label for="code">Code</label>
      <g:textField name="code" value="${warehouse?.code}"/>
    </div>
    <div>
      <label for="name">Name</label>
      <g:textField name="name" value="${warehouse?.name}"/>
    </div>
  </fieldset>
  <g:submitButton name="create" value="Create"/>
</g:form>
<g:link action="index">Back to List</g:link>
</body>
</html>