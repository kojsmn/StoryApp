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
    public int numOfPages = 0;


    public Story(){
    }

    public void getStory(){
    }

    public String getPage(int page){
        return "getPage";
    }

    public void addStory(String title, String author){
    }

    public void deleteStory(){
    }

    public void editStory(){
    }

    public String toString(){
        return "toString";
    }

     public static void main(String a[]) throws Exception {
        Story sl = new Story();
        System.out.println(sl.toString());

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

    }





}

