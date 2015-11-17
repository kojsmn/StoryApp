/*

   Michelle Kojs
   CSE 383

   Edit Controller
   will display form to login and list of stories
 */


package kojsmn;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import kojsmn.models.*;
import java.net.*;


public class Edit extends HttpServlet { 

    Configuration cfg = null;
    String user = "";
    String password = "";
    boolean currentUser = false;
    boolean admin = false;
    int id = 0;
    int page = 0;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response, Configuration cfg) throws
        ServletException, IOException {

            this.cfg = cfg;
            PrintWriter out = response.getWriter();


            // Check to see if user is in Database!
            User u = new User();
            String userCurr = u.getCurrentUser();

            this.user = userCurr;
            currentUser = true;

            //get url
            URL url = new URL(request.getRequestURL().toString());
            String path = url.getPath();
            String parts[] = path.split("/");

            id = Integer.parseInt(parts[4]);
            page = Integer.parseInt(parts[5]);

            System.out.println(path);


            try{
                generatePage(request, out);
            } catch(Exception e){
            }

        }

    public String getEmail(){
        return password;
    }

    public String getUser(){
        return user;
    }

    private void generatePage(HttpServletRequest req, PrintWriter out) throws
        Exception {

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();
            HttpSession session = req.getSession();
            javax.servlet.http.HttpSession sess = req.getSession();

            root.put("CURRENTUSER", user);           
            this.user = user; 

            session.setAttribute("user", user);

            Story s = new Story();
            boolean got = s.getStory(id);
            String content = s.getPage(id, page);
            String title = s.getTitle(id);  
            String author = s.getAuthor(id);

            root.put("TITLE", title);
            root.put("AUTHOR", author);
            root.put("CONTENT", content);
            root.put("ADMIN", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/admin");

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("edit.ftl");

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
}




