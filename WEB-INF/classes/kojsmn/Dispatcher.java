/*
   Michelle Kojs
   CSE 383 - Story App
   11/3/15
 */

package kojsmn;
import kojsmn.Default;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import kojsmn.models.*;

public class Dispatcher extends HttpServlet {
    public Configuration cfg = null;
    public String user = "";
    public Log log = new Log("Story App log");

    public Dispatcher() throws IOException{
        User u = new User();
        u.resetCurrent();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException{
        new Default().doPost(request, response, cfg);
    }


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
                log.log("Going into Story Content " + path);
                new StoryContent().doGet(request,response,cfg);
            }
            else if ("admin".equals(parts[3]){
                if ("manage".equals(parts[4]){
                    new Manage().doGet(request, response, cfg);
                }
                else if ("edit".equals(parts[4]){
                    new Edit().doGet(request, reponse, cfg);
                }               
                else if ("delete".equals(parts[4]){
                    new Delete().doGet(request, response, cfg);
                }
            }
            else {
                log.log("Going into Default " + path);
//                new Default().doPost(request,response,cfg);
                doPost(request, response);

            }
                
        } else{
            log.log("Going into Default " + path);
    //       new Default().doPost(request,response,cfg);
            doPost(request, response);
        }
    }

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }
}
