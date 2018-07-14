<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<@sec.authorize access="isAuthenticated()">
    <a href="/logout"><h1>Logout</h1></a>
</@sec.authorize>
<@sec.authorize access="!isAuthenticated()">
    <a href="/login"><h1>Login</h1></a>
</@sec.authorize>



Get purchased tickets for event
<form method="get" action="/bm/ticketsForEvent">
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
<form method="post" action="/auth/bookTickets" enctype="multipart/form-data">
    Tickets CSV file
    <input type="file" name="ticketsCsv">
    User ID
    <input type="number" name="userId">
    <input type="submit" value="Submit" />
</form>
<br><br>
Get booked tickets in PDF format
<form method="get" action="/bm/ticketspdf">
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