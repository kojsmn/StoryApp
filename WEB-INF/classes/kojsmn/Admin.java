/*
   Michelle Kojs
   CSE 383

   Admin Controller
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


public class Admin extends HttpServlet {

    Configuration cfg = null;
    String user = "";
    String password = "";
    boolean currentUser = false;
    boolean admin = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response, Configuration cfg) throws ServletException, IOException{
        this.cfg = cfg;
        PrintWriter out = response.getWriter();
        admin = true;
        //            new Delete().doGet(request, response, cfg);
        Story s = new Story();
        s.delete(request.getParameter("story"));

        try{
            generatePage(request, out);
        } catch(Exception e){
        }


    }

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
            Map<String,Object> root = new HashMap<String,Object>();
            HttpSession session = req.getSession();
            root.put("WELCOME", "Welcome to the Story Reader!");
            javax.servlet.http.HttpSession sess = req.getSession();
            root.put("SESSIONID", sess.getId());

            root.put("CURRENTUSER", user);
            this.user = user;

            // Get all the stories
            Story s = new Story();
            HashMap<String, String> stories = s.getStoryList();

            root.put("STORIES", stories);


            root.put("DELETESTORY1", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/admin");
            root.put("EDITSTORY1", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/admin/edit");

            root.put("MANAGEUSERS", "admin/manage");

            session.setAttribute("user", user);


            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("admin.ftl");

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

