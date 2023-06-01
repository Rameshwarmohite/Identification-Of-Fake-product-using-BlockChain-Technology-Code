/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Statement;
import pack.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

/**
 *
 * @author Dell
 */
public class ureg extends HttpServlet {

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
            HttpSession user = request.getSession(true);
            String fname = request.getParameter("fname");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String cpass = request.getParameter("cpass");
            String address = request.getParameter("address");
            String mobile = request.getParameter("mobile");
            String bankname = request.getParameter("bankname");
            String branchname = request.getParameter("branchname");
            String account = request.getParameter("account");
            String balance = request.getParameter("balance");
            String bankaddress = request.getParameter("bankaddress");

            DBConnection db = new DBConnection();
            Statement st = db.st;
            ResultSet rs = st.executeQuery("select * from userinfo where email='" + email + "'");
            if (rs.next()) {
                response.sendRedirect("index.jsp?present='failed'");
            } else {
                String str = "insert into userinfo values('" + email + "','" + pass + "','" + fname + "','" + address + "','" + mobile + "')";
                int i = st.executeUpdate(str);
                if (i > 0) {
//                    str = "insert into bankdetails values('" + email + "','" + bankname + "','" + branchname + "','" + account + "','" + balance + "','" + bankaddress + "')";
//                    i = st.executeUpdate(str);
//                    if (i > 0) {
//
//                        Statement stt = db.st;
//
//                        ArrayList at = new ArrayList();
//                        rs.close();
//
//                        rs = stt.executeQuery("select password from imagedetails");
//                        while (rs.next()) {
//                            at.add(rs.getString(1));
//                        }
//
//
//                        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";
//                        Random random = new Random();
//
//                        StringBuilder b = new StringBuilder();
//
//                        for (int g = 0; g < 5; g++) {
//                            b.append(base.charAt(random.nextInt(base.length())));
//                        }
//
//                        while (at.contains(b.toString())) {
//                            b = new StringBuilder();
//
//                            for (int g = 0; g < 5; g++) {
//                                b.append(base.charAt(random.nextInt(base.length())));
//                            }
//                        }
//
//                        pass = b.toString();
//
//                        System.out.println("pasword " + pass);
//                        ServletContext sc = this.getServletContext();
//                        System.out.println("sc " + sc.getContextPath());
//                        System.out.println("rl " + sc.getRealPath("/"));
//
//                        String sg = sc.getRealPath("/");
//                        String path = sg.substring(0, sg.indexOf("build"));
//                        path = path + "Web\\shares\\";
//
//                        //String fn=pass+".png";
//                        String fn = pass + ".jpg";
//                        String fpath = path + fn;
//
//                        String in1 = "";
//                        String out1 = fpath;
//                       
//                        String ms =account;
//
//
//                        in1 = path + "black.png";
//
//
//                        CreateImage ci = new CreateImage(in1, out1, ms);
//                        boolean bool = ci.create();
//
//
//                        if (bool) {
//                            st.executeUpdate("insert into imagedetails values('" + fn + "','" + pass + "','" + email + "')");
//                            
//                            response.sendRedirect("index.jsp");
//                        }
//                        response.sendRedirect("index.jsp");
//                    }
                     response.sendRedirect("index.jsp");
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
