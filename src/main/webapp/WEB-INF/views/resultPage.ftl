<html>
<head>
    <title>Result page</title>
</head>
<body>
    Occupied seats :
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Seats</th>
        </tr>
        <#list purchasedTicketsForEvent as ticket>
          <tr>
              <td>${ticket.id}</td>
              <td>${ticket.seats}</td>
          </tr>
        </#list>
    </table>
</body>
</html>