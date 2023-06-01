/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import pack.AES;

/**
 *
 * @author PHOENIX DINESH
 */
public class GenerateQR extends HttpServlet {

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
//        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String prod_id = AES.decrypt(request.getParameter("id"));
            String path = request.getSession().getServletContext().getRealPath("/");
            //JOptionPane.showMessageDialog(null, "Path--" + path);
            String patt = path.replace("\\build", "");
            generateQR(patt, prod_id);
            String qrfn = path + "\\qr_codes\\" + prod_id + ".png";
            response.setContentType("image/jpeg");
            ServletOutputStream out;
            out = response.getOutputStream();
            FileInputStream fin = new FileInputStream(qrfn);

            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch = 0;;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }

            bin.close();
            fin.close();
            bout.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateQR(String path, String text) {
        try {
            String qrfn = path + "\\qr_codes\\" + text + ".png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(text.getBytes(charset), charset), BarcodeFormat.QR_CODE, 100, 100, hintMap);
            MatrixToImageWriter.writeToFile(matrix, qrfn.substring(qrfn.lastIndexOf('.') + 1), new File(qrfn));

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
