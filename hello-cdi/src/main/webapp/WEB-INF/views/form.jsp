<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MVC 1.0 Hello Demo</title>
    </head>
    <body>
        <p>An empty first and/or last name will result in a validation error</p>
        <form name="form" action="" method="post">
            <p>

                <label id="firstName">First Name:</label>
                <input type="text" name="firstName">
                <font color="red">${mvc.encoders.html(messages.getMessage("firstName"))}</font>
            </p>
            <p>
                <label id="lastName">Last Name:</label>
                <input type="text" name="lastName">
                <font color="red">${mvc.encoders.html(messages.getMessage("lastName"))}</font>
            </p>
            <input type="submit" value="Submit" name="button">
        </form>
    </body>
</html>
