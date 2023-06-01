/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import pack.DBConnection;

/**
 *
 * @author Dell
 */
public class SetPassword extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            String pass = "";
            String email = session.getAttribute("email").toString();
            String count = session.getAttribute("count").toString();
            if(count.equals("0"))
            {
                response.sendRedirect("mens.jsp?item='failed'");
            }
            DBConnection db = new DBConnection();
            Statement st = db.st;

            ServletContext sc = this.getServletContext();
            String sg = sc.getRealPath("/");
            String path = sg.substring(0, sg.indexOf("build"));

            ResultSet rs2 = st.executeQuery("select password from imagedetails where email='" + email + "'");
            if (rs2.next()) {
                pass = rs2.getString(1);
            }
            String s1 = pass;
            String s2 = pass + ".jpg";
            String fp = path + "web\\shares\\" + s2;

            ImageIcon ic1 = new ImageIcon(fp);
            Image img = ic1.getImage();
            int h = img.getHeight(null);
            int w = img.getWidth(null);

            String fp2 = path + "web\\shares\\" + s1;

            ImageShare enc = new ImageShare(h, w, 2);
            boolean bool = enc.initEncrypt(img);
            System.out.println("bool : " + bool);
            enc.encrypt();
            enc.storeImage(fp2);
            response.sendRedirect("userpage1.jsp");
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
