<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Warehouses</title>
</head>
<body>
<h1>Warehouses</h1>
<g:link action="create">Create New Warehouse</g:link>
<table>
  <thead>
  <tr>
    <th>Code</th>
    <th>Name</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${warehouses}" var="warehouse">
    <tr>
      <td>${warehouse.code}</td>
      <td>${warehouse.name}</td>
      <td>
        <g:link action="show" id="${warehouse.id}">Show</g:link>
        <g:link action="edit" id="${warehouse.id}">Edit</g:link>
        <g:link action="delete" id="${warehouse.id}" onclick="return confirm('Are you sure?');">Delete</g:link>
      </td>
    </tr>
  </g:each>
  </tbody>
</table>
</body>
</html>