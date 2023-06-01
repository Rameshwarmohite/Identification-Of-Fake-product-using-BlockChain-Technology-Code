<%-- 
    Document   : payment
    Created on : 7 May, 2016, 4:29:01 PM
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
            HttpSession user = request.getSession(true);
            String email = "";
            String total = "0.00";
            String count = "0";

             if (user.getAttribute("email") == null) {
             user.setAttribute("login", "failed");

             response.sendRedirect("index.jsp");
             } else {
             email = user.getAttribute("name").toString();
             }
             if (user.getAttribute("cart") != null) {
             total = user.getAttribute("total").toString();
             count = user.getAttribute("count").toString();
            // String cart=user.getAttribute("cart").toString();
             
             request.getSession().removeAttribute("cart");
             out.println("alert('Payment is successfully !!!');");
             response.sendRedirect("index.jsp");
             }
             

        %>
    </body>
</html>
