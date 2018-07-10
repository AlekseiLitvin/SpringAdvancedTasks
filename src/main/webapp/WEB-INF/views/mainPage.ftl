<html>
<head>
    <title>Main page</title>
</head>
<body>
Get purchased tickets for event
<form method="get" action="/ticketsForEvent">
    Event name
    <input type="text" name="eventName"><br>
    Event date
    <input type="datetime-local" name="eventDate"><br>
    Auditorium name
    <input type="text" name="auditoriumName"><br>
    <input type="submit" name="Find">
</form>
<br>
<br>
Book tickets:
<form method="post" action="/bookTickets" enctype="multipart/form-data">
    Tickets CSV file
    <input type="file" name="ticketsCsv">
    User ID
    <input type="number" name="userId">
    <input type="submit" value="Submit" />
</form>
<br><br>
Get booked tickets in PDF format
<form method="get" action="/ticketspdf">
    Event name
    <input type="text" name="eventName"><br>
    Event date
    <input type="datetime-local" name="eventDate"><br>
    Auditorium name
    <input type="text" name="auditoriumName"><br>
    <input type="submit" name="Generate">
</form>

<h1>${(bookingMessage)!}</h1>

</body>
</html>