import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author celso
 */



public class HelloClass {
    
    
    public Vector<String> getMsg() throws SQLException{
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
        int i = 0;
        while (rs.next()) {
           
                    String columnValue = rs.getString(1);
                    queries.add(i,columnValue);
                    i++;
        }
//        String temp;    
//        for(int j = 0; j<queries.size()/2; j++) 
//        {   
//            temp = queries.get(j);
//            queries.set(j, queries.get(queries.size())); 
//            queries.set(queries.size(), temp);
//        }

        SqlConnect.closeConnection();
        return queries;
}
    
public boolean insertMsg(String msg) throws SQLException
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

public String plus_a(String in) {
    return in + "AAAAAAAA";
}

public int plus_4(int i) {
    return i + 4;
}  
}
