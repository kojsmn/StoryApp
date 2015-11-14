/*

    Michelle Kojs
    CSE 383

    Story Controller
*/

public class Story extends HttpServlet {
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
        root.put("REMOTEIP", req.getRemoteAddr());
        root.put("REMOTEPORT", Integer.toString(req.getRemotePort()));
        javax.servlet.http.HttpSession sess = req.getSession();
root.put("SESSION", sess.getId());
        root.put("SESSIONTIME", formatTime(sess.getCreationTime()));
        String name = req.getParameter("name");
        if (name==null)
            name = "";
        String param2 = req.getParameter("param2");
        if (param2==null)
            param2="";
        root.put("PARAM1", name);
        root.put("PARAM2", param2);
        root.put("TIME", Long.toString(java.lang.System.currentTimeMillis()));

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

