
public class ScheduleReader {

	public static Schedule read(String route) {
		// TODO: implement actual code
		Schedule schedule = new Schedule();
		schedule.route = route;
		schedule.departureLocation = "altunizade";
		schedule.destinationLocation = "cekmekoy";
		
		String[] departureHours = {"7:45", "8:30", "9:00", "10:00"};
		schedule.departureHours = departureHours;
		
		return schedule;
	}
	
}
