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
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import pack.DBConnection;

/**
 *
 * @author Dell
 */
public class logincheck extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(true);
            String email = request.getParameter("uemail");
            String pass = request.getParameter("upass");
            String str = "select * from userinfo where email='" + email + "' and password='" + pass + "'";
            if (email.equals("admin") && pass.equals("admin")) {
                out.println("<script>");
                out.println("alert('login success');");
                out.println("location='AdminHome.jsp';");
                out.println("</script>");
//                response.sendRedirect("AdminHome.jsp");
            } else {
                DBConnection db = new DBConnection();
                Statement st = db.st;
                ResultSet rs = st.executeQuery(str);
                if (rs.next()) {
                    session.setAttribute("email", email);
                    String name = rs.getString("fname");
                    if (name.contains(" ")) {
                        name = name.substring(0, name.indexOf(" "));
                    }
                    session.setAttribute("name", name);
//                    response.sendRedirect("index.jsp?status='true'");

                    out.println("<script>");
                    out.println("alert('login success');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                } else {
                    str = "select * from murchants where email='" + email + "' and password='" + pass + "'";
                    rs.close();
                    rs = st.executeQuery(str);
                    if (rs.next()) {
                        session.setAttribute("email", email);
                        session.setAttribute("name", email);
//                        response.sendRedirect("adminorder.jsp");
                        out.println("<script>");
                        out.println("alert('login success');");
                        out.println("location='adminorder.jsp';");
                        out.println("</script>");
                    } else {
//                        response.sendRedirect("index.jsp?failed='true'");
                        out.println("<script>");
                        out.println("alert('login failed');");
                        out.println("location='index.jsp';");
                        out.println("</script>");

                    }
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
