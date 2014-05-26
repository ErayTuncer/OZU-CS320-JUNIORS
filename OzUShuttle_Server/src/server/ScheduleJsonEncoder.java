package server;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ScheduleJsonEncoder {
	
	public static JSONObject generateJson(Schedule schedule) {
		JSONObject scheduleJson = new JSONObject();
		
		scheduleJson.put("route", schedule.route);
		scheduleJson.put("departureLocation", schedule.departureLocation);	
		scheduleJson.put("destinationLocation", schedule.destinationLocation);		
		scheduleJson.put("weekdayHours", getJsonArray(schedule.weekdayHours));
		scheduleJson.put("weekendHours", getJsonArray(schedule.weekendHours));
		scheduleJson.put("holidayHours", getJsonArray(schedule.holidayHours));
		
		return scheduleJson;
	}

	private static Object getJsonArray(String[] stringArray) {
		JSONArray jsonArray = new JSONArray();
		for (String string : stringArray) {
			jsonArray.add(string.toString());
		}
		return jsonArray;
	}

}
