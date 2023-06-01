<%-- 
    Document   : logout
    Created on : 26 May, 2016, 4:33:24 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
           HttpSession user=request.getSession();
           user.removeAttribute("email");
           user.removeAttribute("cart");
           response.sendRedirect("index.jsp");
       %>
    </body>
</html>
