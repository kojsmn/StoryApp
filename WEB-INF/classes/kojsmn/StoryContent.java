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
import java.net.*;


public class StoryContent extends HttpServlet {
    Configuration cfg = null;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response, Configuration cfg) throws
        ServletException, IOException {
            this.cfg = cfg;
            PrintWriter out = response.getWriter();
            try {
                this.generatePage(request,out);
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }


    private void generatePage(HttpServletRequest req, PrintWriter out) throws
        Exception {
            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();


            root.put("WELCOME", "Welcome to Story Reader");
            root.put("TIME",
                    Long.toString(java.lang.System.currentTimeMillis()));
            root.put("REMOTEIP", req.getRemoteAddr());
            javax.servlet.http.HttpSession sess = req.getSession();
            root.put("SESSIONID", sess.getId());

            //        Quiz q = inew Quiz();
            //        String user = q.getUser();
            //       String email = q.getEmail();

            String user = System.getProperty("user");
            String email = System.getProperty("email");

            System.out.println("user afsd: " + user);
            if (user != null)
                root.put("USER", user);
            else
                root.put("USER", null);

            if (email != null)
                root.put("EMAIL", email);
            else
                root.put("EMAIL", null);

            //        File sty = new
            //        File("http://kojsmn.383.csi.miamioh.edu:8080/story/stories/story1.sty");

            //      Scanner scan = new Scanner(sty);
            String content = "";

            try {
                URL story1 = new
                    URL("http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story/story1.sty");
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(story1.openStream()));
                String line = "";

                while((line = in.readLine()) != null){
                    content = content + line;
                }

                in.close();

            } catch(MalformedURLException me){
                System.out.println("malformed URL");
            } catch(IOException ioe){
                System.out.println("IOException");
            }

            //   while (scan.hasNextLine()){
            //      content = content + scan.nextLine();
            //  }

            root.put("TEST", "testttting");
            root.put("HOME","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story/");
            root.put("CONTENT", content);
            root.put("YOTEST",
                    "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story/story1.sty");

            root.put("PREVPAGE", null);
            root.put("NEXTPAGE", null);

            root.put("PREVSTORY", null);
            root.put("NEXTSTORY","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story/story2");

            HttpSession session = req.getSession();
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
}

