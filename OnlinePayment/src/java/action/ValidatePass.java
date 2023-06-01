/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import pack.DBConnection;
import pack.MailUtil;
import pack.Order;

/**
 *
 * @author admin
 */
@WebServlet(name = "ValidatePass", urlPatterns = {"/ValidatePass"})
public class ValidatePass extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String total = "0.00";
            double total1 = 0;
            String count = "0";
            String status = "pending";
            HttpSession sn = request.getSession();
            if (sn.getAttribute("cart") != null) {
                total = sn.getAttribute("total").toString();
                count = sn.getAttribute("count").toString();
            }
            String uname = sn.getAttribute("email").toString();

            String pass = request.getParameter("shpass");

            DBConnection db = new DBConnection();
            Statement st = db.st;

            String pass2 = "";
            ResultSet rs2 = st.executeQuery("select password from imagedetails where email='" + uname + "'");
            if (rs2.next()) {
                pass2 = rs2.getString(1);
                // total1 = rs2.getDouble(2);

            }

            if (pass.equals(pass2)) {
                ArrayList cart = null;
                ArrayList bag = new ArrayList();
                // DBConnection db = new DBConnection();
                ArrayList<Order> order = new ArrayList<Order>();
                cart = (ArrayList) sn.getAttribute("cart");
                String items = "";
                if (cart != null) {
                    for (int i = 0; i < cart.size(); i++) {
                        String id = cart.get(i).toString();
                        ResultSet rr = st.executeQuery("select * from items where id='" + id + "'");
                        if (rr.next()) {
                            Order order1 = new Order();
                            order1.id = id;
                            order1.name = rr.getString("name");
                            order1.price = Double.parseDouble(rr.getString("dprice"));
                            order1.count = 1;
                            order.add(order1);
                        }

                        items = items + "" + id + "#";
                    }
                }

                if (items.contains("#")) {
                    items = items.substring(0, items.lastIndexOf("#"));
                }

                status = "pending";

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                //get current date time with Date()
                java.util.Date date = new java.util.Date();//declare date object
                String time = dateFormat.format(date);
                String query = "";
                query = "insert into orders values('0','" + uname + "','" + items + "','" + time + "','" + status + "','" + total + "','" + count + "')";
                st.executeUpdate(query);
                sn.removeAttribute("total");
                sn.removeAttribute("count");
                sn.removeAttribute("cart");
                sn.setAttribute("OrderPlaced", "true");
                rs2.close();
                String orderid = "";
                rs2 = st.executeQuery("select max(orderid) as orderid FROM orders");
                if (rs2.next()) {
                    orderid = rs2.getString(1);

                }
                rs2.close();
                ArrayList at = new ArrayList();
                rs2 = st.executeQuery("select * from murchants");
                while (rs2.next()) {
                    at.add(rs2.getString("email"));
                }
                Random rnd = new Random();
                String body = "Welcome,\n you have a new order.\nOrder Details As below:\n Order Id '" + orderid + "'\n";
                for (int i = 0; i < order.size(); i++) {
                    Order o1 = (Order) order.get(i);

                    body = body + "\t" + (i+1) + ".\t" + o1.name + "\t" + o1.price + "\t" + o1.count + "\n";
                }
                body=body+"\n Total Amount: "+total;
                String murchant = at.get(rnd.nextInt(at.size())).toString();
                MailUtil mail = new MailUtil();
                String[] bcc = {murchant};
                String[] to = {uname};
                mail.sendMail(to, bcc, "New Order",body);
                st.executeUpdate("insert into orderformurchats values('"+orderid+"','"+murchant+"','"+status+"')");
                response.sendRedirect("index.jsp");
            } else {
                sn.setAttribute("Result", "Invalid Password");
                response.sendRedirect("UserPage4.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
