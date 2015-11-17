/*

   Michelle Kojs   
   CSE 383

   User Model

 */

package kojsmn.models;
import java.util.*;
import java.sql.*;
import org.json.*;
import java.io.IOException;

public class User{
    public String username = "";
    public String password = "";
    public int id = -1;
    public boolean admin = false;

    Database db = null;

    public User() throws IOException {
        db = new Database();
    }

    public boolean verifyUser(String username, String password){
        try {
            String sql = "SELECT id FROM User WHERE (username=? and password = sha2(?, 512))";
            PreparedStatement stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt(1);
                updateToCurrentUser(username);
                return true;
            }
            else{
                return false;
            }
        } catch(Exception err){
            System.err.println("Error on verifing User " + err.toString());
            return false;
        } 
    }

    public boolean admin(String username){
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            sql = "SELECT Admin FROM User WHERE username=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()){
                admin = rs.getBoolean(1);
                if (admin == true)
                    return true;
            }
        } catch (Exception err){
            System.err.println(err);
        }

        return false;
    }

    public void updateToCurrentUser(String user){
        String sql;
        PreparedStatement stmt;

        try {
            sql = "UPDATE User SET CurrentUser = true WHERE username=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.executeUpdate();

        } catch (Exception err){
            System.err.println(err);
        }
    }

    public String getCurrentUser(){
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            sql = "SELECT username FROM User WHERE CurrentUser = true";
            stmt = db.conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()){
                return rs.getString(1);
            }

        } catch (Exception err){
            System.err.println(err);
            return null;
        }
        return null;
        

    }

    public void resetPassword(String username){
    }

    public void addUser(String username, String password){
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        // See if username exists
        try {
            sql = "SELECT id FROM User WHERE username=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()){
                System.out.println("Username already exists");
            }
        } catch (Exception err){
            System.err.println("Error checking for username");
        }

        // Insert into table
        try {
            sql = "INSERT INTO User (username, password) VALUES (?, sha2(?, 512))";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }   catch (Exception err){
            System.err.println("Error creating user");
        }

        // Get id for new user
        try {
            sql = "SELECT id FROM User WHERE username=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()){
                id = rs.getInt(1);
            } else {
                System.out.println("Could not get id for new user");
            }
        } catch (Exception err){
            System.err.println("Error getting id for new user");
        }
    }

    public void deleteUser(String username, String password){
    }

    public String toString(){
        return "Username: " + username + " id: " + id;
    }

    public static void main(String a[]) throws Exception {
        User u = new User();
        System.out.println(u.toString());

        // Test Cases
        System.out.println("Testing test good - " + u.verifyUser("test", "test"));

        System.out.println("Testing campbest fail - " + u.verifyUser("test","adsf"));
        System.out.println("Testing campbest empty - " + u.verifyUser("campbest",""));
        System.out.println("Testing empty - " + u.verifyUser("",""));

        System.out.println("Testing invalid user - " + u.verifyUser("asdfasdf","asdfasdf"));
        System.out.println("Testing admin - yes  " + u.admin("Michelle81"));

        System.out.println("Testing admin - no  " + u.admin("test"));
    }
}
