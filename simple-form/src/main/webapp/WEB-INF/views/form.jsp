<%-- 
    Document   : form
    Created on : Mar 29, 2017, 10:27:24 AM
    Author     : Ivar Grimstad (ivar.grimstad@gmail.com)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple MVC 1.0 Form</title>
    </head>
    <body>
        <h1>Simple Form</h1>
        
        <form method="post" action="hello">
            Name: <input type="text" name="name"/>
            <input type ="submit"/>
            <!--<input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>-->
        </form>
    </body>
</html>
