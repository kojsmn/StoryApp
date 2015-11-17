/*

   Michelle Kojs
   CSE 383

   Default Controller
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


public class Delete extends HttpServlet { 

    Configuration cfg = null;
    String user = "";
    String password = "";
    boolean currentUser = false;
    int id = 0;


    protected void doGet(HttpServletRequest request,
            HttpServletResponse response, Configuration cfg) throws
        ServletException, IOException {

            this.cfg = cfg;
            PrintWriter out = response.getWriter();


            // Check to see if user is in Database!
           
            // get story id
        URL url = new URL(request.getRequestURL().toString());
        String path = url.getPath();
        String parts[] = path.split("/");

        System.out.println(path);

        id = Integer.parseInt(parts[5]);
        
        Story s = new Story();
        s.delete(request.getParameter("story"));
             
        }

    private void generatePage(HttpServletRequest req, PrintWriter out) throws
        Exception {

            /* Create a data-model */
            Map<String,String> root = new HashMap<String,String>();
            HttpSession session = req.getSession();
            javax.servlet.http.HttpSession sess = req.getSession();
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




