<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Show Product</title>
  <style>
  </style>
</head>
<body>
<div class="container">
  <h1>Show Product</h1>
  <div class="product-details">
    <p><span>Code:</span> ${productInstance.code}</p>
    <p><span>Name:</span> ${productInstance.name}</p>
    <p><span>Price:</span> ${productInstance.price}</p>
    <p><span>Production Date:</span> ${productInstance.productionDate}</p>
    <p><span>Deadline:</span> ${productInstance.deadline ?: 'Not set'}</p> <!-- Handle null gracefully -->
  </div>
  <div class="actions">
    <g:link action="edit" id="${productInstance.id}">Edit</g:link>
    <g:link action="index">Back to List</g:link>
  </div>
</div>
</body>
</html>