/* 
    Michelle Kojs
    CSE 383

    Database Model
*/

package kojsmn.models;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.IOException;

public class Database{
    String username = "Michelle";
    String password = "Michelle";
    String dbURL = "jdbc:mysql://localhost/StoryApp";

    public Connection conn = null;

    public Database() throws IOException{
        connect();
    }

    public void connect() throws IOException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch(Exception err) {
            System.err.println("Error: " + err);
        }         
    }

    public String getAllDBData(){
        StringBuffer sb = new StringBuffer();
        try{
            Statement stmt = conn.createStatement();
            String sql = "select username, password, id from user";
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                int id = Integer.parseInt(rs.getString("id"));
        
                sb.append(username + " " + password + " " + id + "\n");
            }
        
            rs.close();
            stmt.close();
        } catch (Exception err) {
            sb.append("Error fetching database data");
            System.err.println(err);
            err.printStackTrace();
        }
        return sb.toString();
    }
 
}

