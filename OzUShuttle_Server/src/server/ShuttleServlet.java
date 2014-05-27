package src.server;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ShuttleServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
        String route = getRoute(req.getRequestURL().toString());
        if (Route.hasRoute(route)) {
            XMLParser.parse(route); // TODO remove!
        	writeSchedule(XMLParser.parse(route), resp.getWriter());	
        } else {    	
        	resp.getWriter().println("Not route found!"); // TODO: improve	
        }
	}


	private void writeSchedule(Schedule schedule, PrintWriter printWriter) throws IOException {
		JSONObject scheduleJson = ScheduleJsonEncoder.generateJson(schedule);
        printWriter.println(scheduleJson); 		
	}

	private String getRoute(String target) {
		return target.substring(target.lastIndexOf("/") + 1); // to remove '/'
	}
	
}
