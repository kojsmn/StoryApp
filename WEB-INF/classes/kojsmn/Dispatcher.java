/*
   Michelle Kojs
   CSE 383 - Story App
   11/3/15
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


public class Dispatcher extends HttpServlet {
    String user;
    String email;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException
        {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            user = request.getParameter("user");
            email = request.getParameter("email");
                request.setAttribute("user", user);

                request.setAttribute("email", email);

            System.getProperties().put("user", user);
            System.getProperties().put("email", email);
 
           try {
                generatePage(request, out);
            } catch(Exception e){
            }

        }


    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        user = request.getParameter("user");
        email = request.getParameter("email");

        try {
            this.generatePage(request,out);
        } catch (Exception e) {
            e.printStackTrace(out);
        }

    }

    public String getEmail(){
        return email;
    }

    public String getUser(){
        return user;
    }

    private void generatePage(HttpServletRequest req, PrintWriter out) throws Exception {
        /* ------------------------------------------------------------------------ */    
        /* You should do this ONLY ONCE in the whole application life-cycle:        */    

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("/opt/jetty/webapps/StoryApp"));
        cfg.setDefaultEncoding("UTF-8");
        //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW);

        /* ------------------------------------------------------------------------ */    
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */    

        /* Create a data-model */
        Map<String,String> root = new HashMap<String,String>();
        HttpSession session = req.getSession();

        root.put("WELCOME", "Welcome to the Story Reader!");
        root.put("TIME", Long.toString(java.lang.System.currentTimeMillis()));
        root.put("REMOTEIP", req.getRemoteAddr());
        javax.servlet.http.HttpSession sess = req.getSession();
        root.put("SESSIONID", sess.getId());

        
        String user = (String)session.getAttribute("user");
        String email = (String)session.getAttribute("email");

        if (user == null){
            root.put("USER", null);
        }
        else {
            root.put("USER", user);
        }

        if (email == null){
            root.put("EMAIL", null);
        }
        else{
                root.put("EMAIL", email);
        }


        root.put("TEST", "testttting");
        root.put("STORYLINK1","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story1"); 
        root.put("STORYLINK2","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story2");
        root.put("STORYLINK3","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story3");
        root.put("STORYLINK4","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story4");

        Integer n = (Integer) session.getAttribute("visits");

        if (n==null)
            n = new Integer(0);
        int nn = n.intValue()+1;
        n=new Integer(nn);

        session.setAttribute("visits",new Integer(nn));
        session.setAttribute("user", user);
        session.setAttribute("email", email);

        root.put("VISITS",n.toString());

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("quiz.ftl");

        /* Merge data-model with template */
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }

    private String formatTime(long t) {
        DateFormat f = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
        return f.format(t);
    }
}
