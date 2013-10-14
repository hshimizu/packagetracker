package com.packagetracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.packagetracker.dao.PackageDao;
import com.packagetracker.dao.TrackItem;

/**
 * Servlet implementation class TrackerServlet
 */
@WebServlet("/tracker")
public class TrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
		 + "Transitional//EN\">\n" + "<HTML>\n"
		 + "<head><title>Track a Package</title></head>\n" + "<body style=\"font:14px arial,sans-serif\">\n");
		out.println("<h1>Track a Package</h1>");
		out.println("<p>Use this form to view the progress of a package:</p>");
		out.println("<form id=\"form1\" name=\"form1\" method=\"get\" action=\"\">");
		out.println(" Package id: ");
		out.println("  <input type=\"text\" name=\"p_id\" id=\"p_id\" />");
		out.println("  <input type=\"submit\" name=\"button\" id=\"button\" value=\"Search\" />");
		out.println("</form>");
		out.println("<p>&nbsp;</p>");
		out.println("<table cellpadding=\"6\" style=\"font:14px arial,sans-serif\">");
		
		if (request.getParameter("p_id") != null) {
			PackageDao pd = new PackageDao(request.getParameter("p_id"));

			Iterator<TrackItem> itr = pd.getResultsIterator();
			while (itr.hasNext()) {
				TrackItem packageEntry = itr.next();
				
				out.println("<tr>");
				out.println("<td>" + packageEntry.getStatusTimestamp() + "</td>");
				out.println("<td>" + packageEntry.getLocation() + "</td>");
				out.println("<td>" + packageEntry.getNotes() + "</td>");
				out.println("</tr>");
			}
		}

		out.println("</table>");
		out.println("</body></html>");
	}

}
