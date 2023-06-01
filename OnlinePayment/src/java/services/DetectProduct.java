/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import pack.DBConnection;

/**
 *
 * @author PHOENIX DINESH
 */
public class DetectProduct extends HttpServlet {

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
        try {
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            String prod_id = request.getParameter("productId");
            String sql = "SELECT * FROM items where id='" + prod_id + "'";
            DBConnection db = new DBConnection();
            Statement st = db.st;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("product_pk_id", prod_id);
                object.put("product_name", rs.getString("name"));
                object.put("product_category_fk", rs.getString("subcat"));
                object.put("product_price", rs.getString("dprice"));
                object.put("product_desc", rs.getString("description"));
                object.put("d_charge", "0.0");
                object.put("product_photo", "images/" + rs.getString("path"));
                array.put(object);
            }
            json.put("ProductInfo", array);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());

        } catch (Exception e) {

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
