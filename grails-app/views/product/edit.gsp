<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<g:form action="update" method="PUT">
  <fieldset>
    <g:hiddenField name="id" value="${productInstance?.id}"/>
    <div>
      <label for="code">Code</label>
      <g:textField name="code" value="${productInstance?.code}"/>
    </div>
    <div>
      <label for="name">Name</label>
      <g:textField name="name" value="${productInstance?.name}"/>
    </div>
    <div>
      <label for="price">Price</label>
      <g:textField name="price" value="${productInstance?.price}"/>
    </div>
    <div>
      <label for="productionDate">Production Date</label>
      <g:datePicker name="productionDate" value="${productInstance?.productionDate}"/>
    </div>
    <div>
      <label for="deadline">Deadline</label>
      <g:datePicker name="deadline" value="${productInstance?.deadline}"/>
    </div>
  </fieldset>
  <input type="hidden" name="_method" value="PUT"/>
  <g:submitButton name="update" value="Update"/>
</g:form>
<g:link action="index">Back to List</g:link>
</body>
</html>