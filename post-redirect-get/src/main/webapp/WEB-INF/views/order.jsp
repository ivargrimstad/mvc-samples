<%-- 
    Document   : order
    Created on : Nov 13, 2015, 2:32:40 PM
    Author     : Ivar Grimstad (ivar.grimstad@gmail.com)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
      <form name="order" action="" method="post">
         <label id="item">Item:</label>
         <input type="text" name="item" value="${order.item}">
         <label id="count">Count:</label>
         <input type="text" name="count" value="${order.count}">
         <input type="submit" value="Submit" name="button">
      </form>
         
    </body>

</html>
