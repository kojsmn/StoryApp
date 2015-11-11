/* Michelle Kojs
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Story1 extends HttpServlet {
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

            if (user != null){
                request.setAttribute("user", user);
            }

            if (user != null){
                request.setAttribute("email", email);
            }
            if (user != null && email != null){
            }

            System.getProperties().put("user", user);
            System.getProperties().put("email", email);
            try{
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

    private void generatePage(HttpServletRequest req, PrintWriter out) throws
        Exception {
            /* ------------------------------------------------------------------------
             * */
            /* You should do this ONLY ONCE in the whole application life-cycle:
             * */

            /* Create and adjust the configuration singleton */
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File("/opt/jetty/webapps/StoryApp"));
            cfg.setDefaultEncoding("UTF-8");
            //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW);

            /* ------------------------------------------------------------------------
             * */
            /* You usually do these for MULTIPLE TIMES in the application
             * life-cycle:   */

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();

            String currentURL = req.getRequestURL().toString();

            root.put("WELCOME", currentURL);
            root.put("TIME", Long.toString(java.lang.System.currentTimeMillis()));
            root.put("REMOTEIP", req.getRemoteAddr());
            javax.servlet.http.HttpSession sess = req.getSession();
            root.put("SESSIONID", sess.getId());

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

            String content = "";
            String content2 = "";

            //            BufferedReader in = null;

            String s1 = "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story1"; 
            String s2 = "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story2";
            String s3 = "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story3";
            String s4 = "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story4";


            try {
                URL story1 = new URL("http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/stories/story1.sty");
                URL story2 = new URL("http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/stories/story2.sty");
                URL story3 = new URL("http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/stories/story3.sty");
                URL story4 = new URL("http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/stories/story4.sty");

                BufferedReader in = new BufferedReader(new InputStreamReader(story1.openStream()));

                if (currentURL.equals(s1.toString())){
                    in = new BufferedReader(new InputStreamReader(story1.openStream()));
                    root.put("PREVPAGE", null);
                    root.put("NEXTPAGE", null);

                    root.put("PREVSTORY", null);
                    root.put("NEXTSTORY","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story2");

                }
                else if (currentURL.equals(s2.toString())){
                    in = new BufferedReader(new InputStreamReader(story2.openStream()));
                    root.put("PREVPAGE", null);
                    root.put("NEXTPAGE", null);

                    root.put("PREVSTORY", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story1");
                    root.put("NEXTSTORY","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story3");

                }
                else if (currentURL.equals(s3.toString())){
                    in = new BufferedReader(new InputStreamReader(story3.openStream()));
                    root.put("PREVPAGE", null);
                    root.put("NEXTPAGE", null);

                    root.put("PREVSTORY", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story2");
                    root.put("NEXTSTORY","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story4");

                }
                else if (currentURL.equals(s4.toString())){
                    in = new BufferedReader(new InputStreamReader(story4.openStream()));
                    root.put("PREVPAGE", null);
                    root.put("NEXTPAGE", null);

                    root.put("PREVSTORY", "http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story3");
                    root.put("NEXTSTORY", null);

                }

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

            root.put("TEST", "testttting");
            root.put("HOME","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/");

            root.put("CONTENT", content);

            root.put("YOTEST","http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/stories/story1.sty");

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

}



