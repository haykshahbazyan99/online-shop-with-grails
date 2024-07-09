<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Product</title>
</head>
<body>
<h1>Create Product</h1>
<g:form controller="product" action="save">
    <fieldset>
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
    <g:submitButton name="create" value="Create"/>
</g:form>
<g:link action="index">Back to List</g:link>
</body>
</html>

