/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pack.DBConnection;

/**
 *
 * @author Dell
 */
public class addmens extends HttpServlet {

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
            String saveFile = "", pname = "", cat = "", price = "", dprice = "", des = "", profession = "", nationality = "", tags = "";
            String sex = "", voterno = "", email = "", bg = "", pstreet = "", cstreet = "", parea = "", carea = "", pcity = "", ccity = "";
            String ppinno = "", cpinno = "", pass = "", repass = "";
            int fileidnum = 0, downloadcount = 0;
            String[] tags1 = new String[20000];
            String contentType = request.getContentType();
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

// Set factory constraints
            factory.setSizeThreshold(4012);
//factory.setRepository("c:");

// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
            //upload.setSizeMax(10024);
// Parse the request
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            byte[] data = null;
            String fileName = null;
// Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //processFormField(item);
                    String name = item.getFieldName();
                    String value = item.getString();

                    if (name.equalsIgnoreCase("productname")) {
                        pname = value;
                    } else if (name.equalsIgnoreCase("productprice")) {
                        price = value;
                    } else if (name.equalsIgnoreCase("productdes")) {
                        des = value;
                    } else if (name.equalsIgnoreCase("productcat")) {
                        cat = value;
                    } else if (name.equalsIgnoreCase("productdprice")) {
                        dprice = value;
                    } else if (name.equalsIgnoreCase("producttags")) {
                        tags = value;
                    }
                } else {
                    data = item.get();
                    fileName = item.getName();
                }
            }
            saveFile = fileName;
            String path = request.getSession().getServletContext().getRealPath("/");
            //JOptionPane.showMessageDialog(null, "Path--" + path);
            String patt = path.replace("\\build", "");

            File ff = new File(patt + "\\images\\" + saveFile);
            // File ff = new File(path + saveFile);
            FileOutputStream fileOut = new FileOutputStream(ff);
            fileOut.write(data, 0, data.length);
            fileOut.flush();
            fileOut.close();
            FileInputStream fis;
            File f = new File(patt + "\\images\\" + saveFile);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //get current date time with Date()
            String[] args = {pname, des, price, cat};
            List list = blockchain.BlockChain.isTransactionDone(args);

            boolean flag = (boolean) list.get(0);
            if (flag) {
                String prev_hash = list.get(1).toString();
                String current_hash = list.get(2).toString();
                String queryString = "insert into items values(0,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement psmnt = null;
                DBConnection db = new DBConnection();
                psmnt = db.con.prepareStatement(queryString);
                fis = new FileInputStream(f);
                psmnt.setString(1, "Mens Wear");
                psmnt.setString(2, cat);
                psmnt.setString(3, pname);
                psmnt.setString(4, des);
                psmnt.setString(5, price);
                psmnt.setString(6, dprice);
                psmnt.setBinaryStream(7, (InputStream) fis, (int) (f.length()));
                psmnt.setString(8, saveFile);
                psmnt.setString(9, tags);
                psmnt.setString(10, prev_hash);
                psmnt.setString(11, current_hash);
                int i = psmnt.executeUpdate();
                if (i > 0) {
                    response.sendRedirect("AddMens.jsp?status='true'");
                } else {
                    response.sendRedirect("AddMens.jsp?failed='true'");
                }
            } else {
                response.sendRedirect("AddMens.jsp?failed='true'");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AddMens.jsp?failed='true'");
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
