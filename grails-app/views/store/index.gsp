<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Stores</title>
</head>
<body>
<h1>Stores</h1>

<table>
    <thead>
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${storeList}" var="store">
        <tr>
            <td>${store.code}</td>
            <td>${store.name}</td>
            <td>${store.address ?: 'N/A'}</td>
            <td>
                <g:link action="show" id="${store.id}">Show</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
