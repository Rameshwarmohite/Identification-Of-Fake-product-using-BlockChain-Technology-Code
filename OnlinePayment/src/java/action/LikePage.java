 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pack.DBConnection;

/**
 *
 * @author SCUBETT-1
 */
public class LikePage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            HttpSession user = request.getSession(true);
            String email = "";
            String total = "0.00";
            String count = "0";
            String id = "";
            id = request.getParameter("id");
            if (user.getAttribute("email") == null) {
                user.setAttribute("login", "failed");
                response.sendRedirect("index.jsp");
            } else {

                email = user.getAttribute("email").toString();

            }
            DBConnection db = new DBConnection();
            Statement st = db.st;
            PreparedStatement ps = null;
            String like = "like";
            String unlike = "unlike";
            String status = "";

            ResultSet rs = st.executeQuery("select * from liketable_ where email='" + email + "' and id='" + id + "'");
            if (rs.next()) {
                status = rs.getString("status");
                if (status.equals(like)) {
                    String sql = "update liketable_ set status='" + unlike + "'  where email='" + email + "' and id='" + id + "'";
                    ps = db.con.prepareStatement(sql);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        System.out.println("unlike");
                        response.sendRedirect("single.jsp?"+id+"");
                    } else {
                        System.out.println("unlike1");
                        response.sendRedirect("single.jsp?"+id+"");
                    }
                } else {
                    String sql = "update liketable_ set status='" + like + "'  where email='" + email + "' and id='" + id + "'";
                    ps = db.con.prepareStatement(sql);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        System.out.println("like");
                        response.sendRedirect("single.jsp?"+id+"");
                    } else {
                        System.out.println("like1");
                        response.sendRedirect("single.jsp?"+id+"");
                    }
                }
            } else {
                String queryString = "insert into liketable_ (email,id,status)values('" + email + "','" + id + "','" + like + "')";
                ps = db.con.prepareStatement(queryString);
                int i = ps.executeUpdate();
                if (i > 0) {
                    System.out.println("like2");
                    response.sendRedirect("single.jsp?"+id+"");
                } else {
                    response.sendRedirect("single.jsp?"+id+"");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
