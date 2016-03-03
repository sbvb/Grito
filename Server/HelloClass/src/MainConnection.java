
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author celso
 */
public  class MainConnection {
    public static void main(String [ ] args) throws SQLException {
        String XML = "<ns:getMsgResponse><ns:return>Mensagem de teste 1</ns:return><ns:return>Mensagem de teste 2</ns:return><ns:return>Mensagem de teste 3</ns:return><ns:return>Mensagem de teste 4</ns:return><ns:return>mensagem de teste xinforinfola</ns:return><ns:return>mensagem de teste xinforinfola</ns:return><ns:return>mensagem de teste xinforinfola</ns:return><ns:return>mensagem de teste xinforinfola</ns:return></ns:getMsgResponse>";
        System.out.println(handXML(XML));
        insertMsg("Testando envio de outro local");
        // connect();
}
       public static String handXML(String str)  {
        String replace = str.replaceAll("<.*?>", " ");
        //String result = "";

        return replace;
    }
         public static Vector<String> connect() throws SQLException{
            Vector<String> queries = new Vector<String>();
            SqlConnect.startConnection();
            Statement stmt = null ;
            try {
                stmt = SqlConnect.con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            String query = "select * from billboardMain ;" ;
            ResultSet rs = stmt.executeQuery(query) ;
            int columnsNumber = rs.getMetaData().getColumnCount();
            int j = 0;
            while (rs.next()) {
                
                        String columnValue = rs.getString(1);
                      //  System.out.println(columnValue);
                        queries.add(j, columnValue); 
                        j++;
            }
            System.out.println(columnsNumber);
            for (int i =0; i < queries.size() ; i++)
            {
                System.out.println(i + "\n " + queries.get(i));
            }
            SqlConnect.closeConnection();
            
            return queries;
    }
     
     public static boolean insertMsg(String msg) throws SQLException
{
    SqlConnect.startConnection();
        Statement stmt = null ;
        try {
            stmt = SqlConnect.con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MainConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        PreparedStatement ps = SqlConnect.con.prepareStatement("INSERT INTO billboardMain"
                + "(message) VALUES" + "( ? )");
        ps.setString(1, msg);
        ps.executeUpdate();
        SqlConnect.closeConnection();
        return true;
}
//    public static void main(String [ ] args) throws SQLException{
//        SqlConnect.startConnection();
//        Statement stmt = null ;
//        try {
//            stmt = SqlConnect.con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        String query = "select * from billboardMain ;" ;
//        ResultSet rs = stmt.executeQuery(query) ;
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        while (rs.next()) {
//        for (int i = 1; i <= columnsNumber; i++) {
//                if (i > 1) System.out.print(",  ");
//                String columnValue = rs.getString(i);
//                System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//        
//
//        System.out.println("");
//        }
//        System.out.println(columnsNumber);
//        SqlConnect.closeConnection();
//        
//        
//    }
//     
    
}
