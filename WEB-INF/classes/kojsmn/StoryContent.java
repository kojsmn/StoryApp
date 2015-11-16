/*
   Michelle Kojs
   CSE 383 - Story App
   11/3/15
 */

package kojsmn;
import kojsmn.models.*;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import java.sql.*;


public class StoryContent extends HttpServlet {
    public Configuration cfg = null;
    public String idStr = null;
    public String pageStr = null;
    public int id = -1;
    public int page = 1;

    public String user = "";
    public String email = "";

    public String author = "";
    public String title = "";

    Story story = null;

    //public Story sty = new Story();

    public StoryContent(){

        try {
            story = new Story();
        } catch(Exception e){
            title = e + " ";
        }
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response, Configuration cfg) throws
        ServletException, IOException {

            this.cfg = cfg;

            PrintWriter out = response.getWriter();
            URL url = new URL(request.getRequestURL().toString());
            String path = url.getPath();
            String parts[] = path.split("/");

            idStr = parts[4];
            id = Integer.parseInt(idStr);

            if (parts.length > 5){
                pageStr = parts[5];
                page = Integer.parseInt(pageStr);
            }

            // Get story stuff
            boolean got = story.getStory(id);
            if (got){
                author = story.getAuthor(id);
                title = story.getTitle(id);
            }

            try {

                this.generatePage(request,out);

            } catch (Exception e) {

                e.printStackTrace(out);

            }

        }




    public void generatePage(HttpServletRequest req, PrintWriter out) throws
        Exception {

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();


            root.put("ID", idStr);

            root.put("AUTHOR", author);
            root.put("TITLE", title);


            // Get Page if on that...
            String content = null;
            content = story.getPage(id, page);

            if (content != null){
                root.put("CONTENT", content);
            }


            // Create User
            User u = new User();

            // Get Username
            //            String user = u.getUser();

            //          String user = System.getProperty("user");
            //        String email = System.getProperty("email");

            if (user != null)
                root.put("USER", user);
            else
                root.put("USER", null);

            if (email != null)
                root.put("EMAIL", email);
            else
                root.put("EMAIL", null);




            // Prev, Next, and Home PAGE links
            root.put("HOME","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/");

            // See if there is a previous page

            if (page > 1) { // there is a previous page
                root.put("PREVPAGE", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/" + id + "/" + (page-1));
            }   
            else {
                root.put("PREVPAGE", null);
            }
            // See if there is a next page
            if (story.getPage(id, (page+1)) != null){
                root.put("NEXTPAGE", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/" + id + "/" + (page+1));
            }
            else {
                root.put("NEXTPAGE", null);
            }

            HttpSession session = req.getSession();

            session.setAttribute("user", user);
            session.setAttribute("email", email);

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("story.ftl");

            /* Merge data-model with template */
            temp.process(root, out);
            // Note: Depending on what `out` is, you may need to call
            // `out.close()`.
            // This is usually the case for file output, but not for servlet
            // output.
        }





    private String formatTime(long t) {
        DateFormat f = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
        return f.format(t);
    }

    public static void main(String a[]) throws Exception{

        //System.out.println("TEsting get Story: " + sty.getStory(1) + " "  + sty.toString(1));

        //        System.out.println("Testing get page: " + sty.getPage(1,1));

    }
}

