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

    public User(){
    }

    public boolean verifyUser(String username, String password){
        return false;
    }

    public boolean admin(String username, String password){
        return false;
    }

    public void resetPassword(String username){
    }

    public void addUser(String username, String password){
    }

    public void deleteUser(String username, String password){
    }

    public String toString(){
        return "toString";
    }

     public static void main(String a[]) throws Exception {
        User u = new User();
        System.out.println(u.toString());

    }

    



}
