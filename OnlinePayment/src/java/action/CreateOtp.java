/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pack.DBConnection;

/**
 *
 * @author Dell
 */
public class CreateOtp extends HttpServlet {

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
            HttpSession session=request.getSession();
            String email=session.getAttribute("email").toString();
            DBConnection db = new DBConnection();
            Statement stt = db.st;

            ArrayList at = new ArrayList();


            ResultSet rs = stt.executeQuery("select password from imagedetails");
            while (rs.next()) {
                at.add(rs.getString(1));
            }


            String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";
           String base1="0123456789";
            Random random = new Random();

            StringBuilder b = new StringBuilder();

            for (int g = 0; g < 5; g++) {
                b.append(base.charAt(random.nextInt(base.length())));
            }

            while (at.contains(b.toString())) {
                b = new StringBuilder();

                for (int g = 0; g < 5; g++) {
                    b.append(base.charAt(random.nextInt(base.length())));
                }
            }

            String pass = b.toString();
            System.out.println("pasword " + pass);
            ServletContext sc = this.getServletContext();
            System.out.println("sc " + sc.getContextPath());
            System.out.println("rl " + sc.getRealPath("/"));

            String sg = sc.getRealPath("/");
            String path = sg.substring(0, sg.indexOf("build"));
            path = path + "Web\\shares\\";

            //String fn=pass+".png";
            String fn = pass + ".jpg";
            String fpath = path + fn;

            String in1 = "";
            String out1 = fpath;

            String ms = pass;


            in1 = path + "black.png";


            CreateImage ci = new CreateImage(in1, out1, ms);
            boolean bool = ci.create();


            if (bool) {
                rs.close();
               String  str="";
                rs=stt.executeQuery("select password from imagedetails where email='"+email+"'");
                if(rs.next())
                {
                    str="update imagedetails set password='"+pass+"', imgName='"+fn+"' where email='"+email+"'";
                }
                else
                {
                    str="insert into imagedetails values('" + fn + "','" + pass + "','" + email + "')";
                }
                stt.executeUpdate(str);
                response.sendRedirect("carddetails.jsp");
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
