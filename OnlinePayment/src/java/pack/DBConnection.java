/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Dell
 */
public class DBConnection {

    public Connection con;
    public Statement st;
    public ResultSet rs;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinepayment", "root", "");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Insert(String Query) {
        int i = 0;
        try {
            con = new DBConnection().con;
            st = con.createStatement();
            i = st.executeUpdate(Query);
            st.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error Insert DataBase class " + ex);

        }
        return i;
    }

    public int Update(String Query) {
        int i = 0;
        try {
            con = new DBConnection().con;
            st = con.createStatement();
            i = st.executeUpdate(Query);
            st.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error Update DataBase class " + ex);

        }
        return i;
    }

    public ResultSet Select(String Query) {

        try {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
            con = new DBConnection().con;
            st = con.createStatement();
            rs = st.executeQuery(Query);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return rs;
    }

}
