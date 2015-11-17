/*

   Michelle Kojs
   CSE 383

   Manage Controller
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


public class Manage extends HttpServlet { 

    Configuration cfg = null;
    String user = "";
    String password = "";
    boolean currentUser = false;
    boolean admin = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response, Configuration cfg) throws ServletException, IOException{
         this.cfg = cfg;
            PrintWriter out = response.getWriter();


            // Check User
            response.setContentType("text/html");
            user = request.getParameter("user");
            password = request.getParameter("password");

            if (user != null){
                request.setAttribute("user", user);
            }

            // Check to see if user is in Database!
                User u = new User();
                currentUser = u.verifyUser(user, password);
                String userCurr = u.getCurrentUser();
                admin = u.admin(user);

            if (currentUser){
                u.updateToCurrentUser(user);
            }
            else if (userCurr != null){
                this.user = userCurr;
                currentUser = true;
            }

             if (admin){
                new Admin().doGet(request, response, cfg);
            }
            else {


            try{
                generatePage(request, out);
            } catch(Exception e){
            }
            }


    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response, Configuration cfg) throws
        ServletException, IOException {

            this.cfg = cfg;
            PrintWriter out = response.getWriter();


            // Check User
            response.setContentType("text/html");
            user = request.getParameter("user");
            password = request.getParameter("password");

            if (user != null){
                request.setAttribute("user", user);
            }

            // Check to see if user is in Database!
                User u = new User();
                currentUser = u.verifyUser(user, password);
                String userCurr = u.getCurrentUser();
                admin = u.admin(user);    
          
            if (currentUser){
                u.updateToCurrentUser(user);
            }
            else if (userCurr != null){
                this.user = userCurr;
                currentUser = true;
            }
            
            if (admin){
                new Admin().doGet(request, response, cfg);
            }
            else {
            try{
                generatePage(request, out);
            } catch(Exception e){
            }
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
            root.put("WELCOME", "Welcome to the Story Reader!");
            javax.servlet.http.HttpSession sess = req.getSession();
            root.put("SESSIONID", sess.getId());

            if (currentUser) {
                root.put("CURRENTUSER", user);           
                this.user = user; 
             
                // Get all the stories
                Story s = new Story();
                HashMap<Integer, String> list = s.getStoryList();

      
            root.put("STORYLINK1","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/1/1");
            root.put("STORYLINK2","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/2/1");
            root.put("STORYLINK3","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/3/1");
            root.put("STORYLINK4","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/4/1");

}
            
            Integer n = (Integer) session.getAttribute("visits");

            if (n==null)
                n = new Integer(0);
            int nn = n.intValue()+1;
            n=new Integer(nn);

            session.setAttribute("visits",new Integer(nn));
            session.setAttribute("user", user);
            //          session.setAttribute("email", email);

            root.put("VISITS",n.toString());

            /* Get the template (uses cache internally) */
                Template temp = cfg.getTemplate("quiz.ftl");

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




