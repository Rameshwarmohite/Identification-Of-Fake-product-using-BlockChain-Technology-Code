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

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import pack.DBConnection;

/**
 *
 * @author admin
 */
@WebServlet(name = "UserShare", urlPatterns = {"/UserShare"})
public class UserShare extends HttpServlet {

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
    public final void image(String mail, String img) {
        // SMTP info
        String host1 = "smtp.gmail.com";
        String port1 = "587";
        String mailFrom = "testingproject2015@gmail.com";
        String password = "testingpassword";
        ServletContext sc = this.getServletContext();
        System.out.println("sc " + sc.getContextPath());
        System.out.println("rl " + sc.getRealPath("/"));

        String sg = sc.getRealPath("/");
        String path = sg.substring(0, sg.indexOf("build"));
        path = path + "Web\\shares\\";
        // message info
        String fpath = path + img;
        String mailTo = mail;
        String subject = "Message from VC_System";
        StringBuffer body = new StringBuffer("<html>This message contains user share.<br>");

        body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");

        //  body.append(msg);
        body.append("</html>");

        // inline images

        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", fpath);

        try {
            EmbeddedImageEmailUtil.send(host1, port1, mailFrom, password, mailTo, subject, body.toString(), inlineImages);
            System.out.println("image sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sn = request.getSession();
            String uname = sn.getAttribute("email").toString();

            DBConnection db = new DBConnection();
            Statement st = db.st;
            Statement st1 = db.st;



            String mail = "";

            ResultSet rs1 = st.executeQuery("select password from imagedetails where email='" + uname + "'");
            String pass = "";
            if (rs1.next()) {
                pass = rs1.getString(1);
            }
            mail = uname;
            String imgname = pass + "sh0.jpg";
            image(mail, imgname);
            sn.setAttribute("UserPass", pass);
            sn.setAttribute("UserPassImg", pass + ".jpg");
            response.sendRedirect("userpage11.jsp");

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
