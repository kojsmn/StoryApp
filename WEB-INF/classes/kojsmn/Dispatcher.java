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

public class Dispatcher extends HttpServlet {
    public Configuration cfg = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {

        /* ------------------------------------------------------------------------
 * */    
            /* You should do this ONLY ONCE in the whole application
 * life-cycle:        */    

            /* Create and adjust the configuration singleton */
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File("/opt/jetty/webapps/StoryApp/"));
            cfg.setDefaultEncoding("UTF-8");
        
        //get url
        PrintWriter out = response.getWriter();
        URL url = new URL(request.getRequestURL().toString());
        String path = url.getPath();
        String parts[] = path.split("/");
        
        System.out.println(path);

        if (parts.length > 3) {
            if ("story".equals(parts[3])) {
                new StoryContent().doGet(request,response,cfg);
            }
            else if ("about".equals(parts[3])) {
//                about(request,out);i
            }
            else 
                new Default().doGet(request,response,cfg);
        } else
            new Default().doGet(request,response,cfg);
    }
}
