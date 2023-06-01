/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import pack.DBConnection;
import pack.MailUtil;

/**
 *
 * @author Dell
 */
public class addcard extends HttpServlet {

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
            String username = request.getParameter("mail");
            String pass = request.getParameter("pass");
            String name = request.getParameter("mname");
            String number = request.getParameter("mobile");
            String address = request.getParameter("address");
            DBConnection db = new DBConnection();
            Statement st = db.st;
            ResultSet rs = st.executeQuery("select * from murchants where email='" + username + "'");
            if (rs.next()) {
                response.sendRedirect("AddCardDetails.jsp?failed='failed'");
            } else {
                int i = st.executeUpdate("insert into murchants values('" + username + "','" + pass + "','" + name + "','" + number + "','" + address + "')");
                if (i > 0) {
                    String[] mail = {username};
                    String body = "Welcome,\n You are register with system.\n Your email '" + username + "' and password '" + pass + "'";
                    MailUtil msg = new MailUtil();
                    msg.sendMail(mail, mail, "Merchant registration", body);
                    response.sendRedirect("AddCardDetails.jsp?status='true'");
                }
            }
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
