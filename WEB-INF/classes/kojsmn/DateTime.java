/**end back the current date and time
 **/

package kojsmn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class DateTime extends HttpServlet
{
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
	{
		httpServletResponse.setContentType("text/html");
		PrintWriter out = httpServletResponse.getWriter();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		out.println("<html><header>Welcome to Story Reader</header><body>This is the lab<br>");
		out.println(dateFormat.format(cal.getTime()));
		out.println("<br><ul>");
		out.println("<li>10.0.0.0/8</li>");
		out.println("<li>172.16.0.0/12</li>");
		out.println("<li>192.168.0.0/16</li>");
		out.println("</ul>");
		out.println("<footer>Footer</footer>");
		out.close();
	}

	//	public static void main(String [] args){	

	//	}
}

