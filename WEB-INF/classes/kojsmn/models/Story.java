/*

   Michelle Kojs
   CSE 383

   Story Model

 */

package kojsmn.models;
import java.util.*;
import java.sql.*;
import org.json.*;
import java.io.IOException;

public class Story{
    public String title = "";
    public String author = "";
    public int id = -1;

    public int numOfPages = 0;
    public String content = "";

    public Database db = null;

    public Story() throws IOException {
        db = new Database();
    }

    public Story(int id) throws IOException {
        db = new Database();
        this.id = id;


    }

    public boolean getStory(int id){
        String sql = "";
        PreparedStatement stmt;
        ResultSet rs;
        this.id = id;

        try {
            sql = "SELECT Title, Author FROM Stories WHERE id=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                this.title = rs.getString(1);
                this.author = rs.getString(2);
                return true;
            }
            else {
                System.err.println("Story id does not exist");
                return false;
            }
        } catch (Exception err){
            System.err.println("Error with getting Story " + err);
            return false;
        }

    }

    public String getPage(int id, int page){
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            sql = "SELECT content FROM Page WHERE id=? AND page=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, page);
            rs = stmt.executeQuery();

            if (rs.next()){
                String pageContent = rs.getString(1);
                return pageContent;
            }
        } catch (Exception err){
            System.err.println("Error getting page content " + err);
            return null;    
        }   

        return null;

    }



    public int addStory(String title, String author) throws IOException {
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            sql = "SELECT id FROM Stories WHERE title=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, title);
            rs = stmt.executeQuery();

            if (rs.next()){
                System.err.println("Error adding Story");
                return -1;
            }
        } catch (Exception err){
            System.err.println("Error adding Story " + err);
            return -1;
        }

        try {
            sql = "INSERT INTO Stories (title, author) VALUES (?,?)";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();

            // Get new id
            sql = "SELECT id FROM Stories WHERE title=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setString(1, title);
            rs = stmt.executeQuery();
            if (rs.next()){
                id = rs.getInt(1);
                return id;
            } else {
                System.err.println("Error getting new id");
                return -1;
            }
        } catch (Exception err){
            System.err.println("Error adding story " + err);
            return -1;
        }
    }

    public boolean deleteStory(int id){
        String sql;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            sql = "DELETE FROM Stories WHERE id=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // check to see if deleted
            sql = "SELECT * FROM Stories WHERE id=?";
            stmt = db.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                System.err.println("Error deleting Story");
                return false;
            }
            else {
                return true;
            }
        } catch (Exception err){
            System.err.println("Error deleting Story " + err);
            return true;
        }

    }


    public HashMap<Integer,String> getStoryList() {
        HashMap<Integer,String> list = new HashMap<Integer,String>();

        String sql = "SELECT id, Title FROM Stories ORDER BY Title";
        try {
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.put(rs.getInt("id"),rs.getString("Title"));
            }
        } catch (Exception err) {
            System.err.println("getStoryList error " + err);
            return null;
        }
        return list;

    }

    public void editStory(){
    }

    public String toString(int id){
        getStory(id);
        return title + " " + author + " " + id;
    }

    public String getAuthor(int id){
        return this.author;
    }

    public String getTitle(int id){
        return this.title;
    }

    public static void main(String a[]) throws Exception {
        //   Story s1 = new Story();

        //        System.out.println("Dump story");
        //        HashMap<Integer,String> stories = sl.getStoryList();
        //        for (Integer pki:stories.keySet()) {
        //            int pk = pki.intValue();
        //            System.out.println("Story " + stories.get(pk));
        //            int n = sl.getNumPages(pk);
        //            for (int j=1;j<n+1;j++ ){
        //                String contents = sl.getPage(pk,j);
        //                System.out.println("page: " + contents);
        //            }
        //            System.out.println();
        //        }

        // Test Cases
        //        System.out.println("Testing get Story: " + s1.getStory(1) + " " + s1.toString(1));
        //       System.out.println("Testing getPage: " + s1.getPage(1, 1));
        //        int id = s1.addStory("Adding", "YO");
        //        System.out.println("Testing addStory: " +  id);
        //        System.out.println("Testing if story added: " + s1.getStory(id) + " " + s1.toString(id));
        // /       System.out.println("Testing deleteStory: " + s1.deleteStory(id));
        //        System.out.println("Testing if story deleted: " + s1.getStory(id));

        //     System.out.println(s1.toString(1));
    }
}

