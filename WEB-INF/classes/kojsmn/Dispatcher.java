/*
   Servlet example using freemarker template
   campbest
   10/15/2015
   cse383
 */

package campbest;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.URL;


public class Dispatcher extends HttpServlet {
    public Configuration cfg = null;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        /* ------------------------------------------------------------------------
         * */    
        /* You should do this ONLY ONCE in the whole application
         * life-cycle:        */    

        /* Create and adjust the configuration singleton */
        cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new
                File("/opt/jetty/webapps/story"));
        cfg.setDefaultEncoding("UTF-8");

        //get url
        PrintWriter out = response.getWriter();
        URL url = new URL(request.getRequestURL().toString());
        String path = url.getPath();
        String parts[] = path.split("/");
        if (parts.length >2) {
            if ("story".equals(parts[2])) {
                new Story().doGet(request,response,cfg);
            }
            else if ("about".equals(parts[2])) {
                about(request,out);
            }
            else 
                new Default().doGet(request,response);
        } else
            new Default().doGet(request,response);
    }


    protected void about(HttpServletRequest req,PrintWriter out, Configuration
            cfg) {
        try {

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("quiz.ftl");

            /* Merge data-model with template */
            temp.process(root, out);
            // Note: Depending on what `out` is, you may need to call
            // `out.close()`.
            // This is usually the case for file output, but not for servlet
            // output.
        } catch (Exception err) {
            out.println("Error");
        }
    }

}
