<%-- 
    Document   : index
    Created on : Jun 9, 2016, 2:50:58 PM
    Author     : Ivar Grimstad (ivar.grimstad@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  response.sendRedirect(request.getContextPath() + "/mvc/reservations/new");
%>