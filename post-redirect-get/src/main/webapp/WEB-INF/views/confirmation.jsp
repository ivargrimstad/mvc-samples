<%-- 
    Document   : order
    Created on : Nov 13, 2015, 2:32:40 PM
    Author     : Ivar Grimstad (ivar.grimstad@gmail.com)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
    </head>
    <body>
        <h1>Reservation confirmed</h1>
        <p>
            <label id="item">Id: </label>
            <font color="blue">${reservation.id}</font>
        </p>
        <p>
            <label id="item">Name: </label>
            ${reservation.name}
        </p>
        <p>
            <label id="count">Count: </label>
            ${reservation.count}
        </p>
        <p>
            <label id="date">Date: </label>
            ${reservation.date}
        </p>
        <p>
            <label id="outside">Outside: </label>
            ${reservation.outside}
        </p>
        <p/>
    </body>

</html>
