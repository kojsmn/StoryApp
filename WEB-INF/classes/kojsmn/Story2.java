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


public class Story2 extends HttpServlet {
    //        String sessionId;
    String user;
    String email;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException
        {
 response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            //  out.println("<html><body>");
            user = request.getParameter("user");
            //  out.println("Welcome "+user);
            email = request.getParameter("email");
            //    out.println("EMAIL " + email);
            //  out.println("</body></html>");
            //  out.close();

            if (user != null){
                request.setAttribute("user", user);
            }

            if (user != null){
                request.setAttribute("email", email);
            }
            if (user != null && email != null){
                //        getServletContext().getRequestDispatcher("Story1").forward(request,response);
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

        //              sessionId = request.getParameter("sessionId");


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
            cfg.setDirectoryForTemplateLoading(new File("/opt/jetty/webapps/story"));
            cfg.setDefaultEncoding("UTF-8");
            //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW);

            /* ------------------------------------------------------------------------
             * */
            /* You usually do these for MULTIPLE TIMES in the application
             * life-cycle:   */

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();
            root.put("WELCOME", "Welcome to Story Reader");
            root.put("TIME", Long.toString(java.lang.System.currentTimeMillis()));
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
                URL story1 = new URL("http://kojsmn.383.csi.miamioh.edu:8080/story/stories/story2.sty");
                BufferedReader in = new BufferedReader(new InputStreamReader(story1.openStream()));
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
            root.put("HOME","http://kojsmn.383.csi.miamioh.edu:8080/story/servlet/");

            root.put("CONTENT", content);
            root.put("YOTEST", "http://kojsmn.383.csi.miamioh.edu:8080/story/stories/story2.sty");

            root.put("PREVPAGE", null);
            root.put("NEXTPAGE", null);

            root.put("PREVSTORY", "http://kojsmn.383.csi.miamioh.edu:8080/story/servlet/story1");
            root.put("NEXTSTORY","http://kojsmn.383.csi.miamioh.edu:8080/story/servlet/story3");

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



