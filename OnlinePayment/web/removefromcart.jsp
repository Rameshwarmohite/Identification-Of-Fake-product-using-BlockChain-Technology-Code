<%-- 
    Document   : removefromcart
    Created on : 7 May, 2016, 1:03:58 PM
    Author     : Dell
--%>

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
            try {
                HttpSession user = request.getSession(true);
                boolean flag = true;
                DBConnection db = new DBConnection();
                Statement st = db.st;
                double price = 0;
                int id = Integer.parseInt(request.getQueryString());
                if (user.getAttribute("cart") == null) {
                    flag = false;
                }
                if (flag) {
                    ArrayList cart = (ArrayList) user.getAttribute("cart");
                    String id1 = cart.get(id).toString();
                    ResultSet rs = st.executeQuery("select dprice from items where id='" + id1 + "'");
                    if (rs.next()) {
                        price = Double.parseDouble(rs.getString("dprice"));
                    }
                    double total = (Double) user.getAttribute("total");
                    int count = (Integer) user.getAttribute("count");
                    count--;
                    total = total - price;
                    cart.add(id);
                    cart.remove(id);
                    user.removeAttribute("cart");
                    user.setAttribute("cart", cart);
                    user.setAttribute("count", count);
                    user.setAttribute("total", total);
                }
                String referer = request.getHeader("Referer");
                response.sendRedirect(referer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </body>
</html>
