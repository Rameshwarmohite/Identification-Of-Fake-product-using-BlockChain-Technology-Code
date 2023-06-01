<%-- 
    Document   : addtocart
    Created on : 7 May, 2016, 1:03:38 PM
    Author     : Dell
--%>

<%@page import="pack.Item"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="pack.DBConnection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try{
            HttpSession user = request.getSession(true);
            boolean flag = true;
            String id = request.getQueryString();
            if (user.getAttribute("cart") == null) {
                flag = false;
            }
            double price=0;
            DBConnection db=new DBConnection();
            Statement st=db.st;
            ResultSet rs=st.executeQuery("select dprice from items where id='"+id+"'");
            if(rs.next())
            {
                price=Double.parseDouble(rs.getString("dprice"));
            }
            if (flag) {
                ArrayList cart = (ArrayList) user.getAttribute("cart");
                double total=(Double)user.getAttribute("total");
                int count=(Integer)user.getAttribute("count");
                count++;
                total=total+price;
                cart.add(id);
                user.removeAttribute("cart");
                user.setAttribute("cart", cart);
                user.setAttribute("count", count);
                user.setAttribute("total", total);
            } else {
                ArrayList cart = new ArrayList();
                cart.add(id);
                user.setAttribute("cart", cart);
                user.setAttribute("total", price);
                user.setAttribute("count", 1);
            }
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        %>
    </body>
</html>
