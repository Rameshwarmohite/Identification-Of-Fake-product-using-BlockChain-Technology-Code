/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pack.DBConnection;
import pack.MailUtil;

/**
 *
 * @author Dell
 */
public class merchantupdateorder extends HttpServlet {

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
            String orderid = request.getParameter("orderid");
            String status = request.getParameter("orderstatus");
            String mail = "";
            DBConnection db = new DBConnection();
            Statement st = db.st;
            int i = st.executeUpdate("update orders set status='" + status + "' where orderid='" + orderid + "'");
            i = st.executeUpdate("update orderformurchats set status='" + status + "' where orderid='" + orderid + "'");
            ResultSet rs = st.executeQuery("select email from orders where orderid='" + orderid + "'");
            if (rs.next()) {
                mail = rs.getString(1);
            }
            String[] email = {mail};
            String body = "Welcome,\nOrder id '" + orderid + "' is delivered by merchant.\n Please collect your Order";
            MailUtil msg = new MailUtil();
            msg.sendMail(email, email, "Order Delivered..", body);
            response.sendRedirect("MerchantOrder.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
