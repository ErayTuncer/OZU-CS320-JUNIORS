
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONObject;


public class ShuttleHttpHandler extends AbstractHandler {

	public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new ShuttleHttpHandler());
        
        server.start();
        server.join();
	}

	@Override
	public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse httpResponse)
			throws IOException, ServletException {
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        
        String route = getRoute(target);
        if (Schedule.hasRoute(route)) {
            baseRequest.setHandled(true);
        	writeSchedule(ScheduleReader.read(route), httpResponse);	
        } else {
            baseRequest.setHandled(true);        	
            httpResponse.getWriter().println("Not route found!"); // TODO: improve	
        }
	}

	private void writeSchedule(Schedule schedule, HttpServletResponse httpResponse) throws IOException {
		JSONObject scheduleJson = ScheduleJsonEncoder.generateJson(schedule);
        httpResponse.getWriter().println(scheduleJson); 		
	}

	private String getRoute(String target) {
		return target.substring(1); // to remove '/'
	}


}
