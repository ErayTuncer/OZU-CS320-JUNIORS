
public class Schedule {
	
	public static final String ROUTE_ALTUNIZADE_CEKMEKOY = "altunizade-cekmekoy";
	public static final String ROUTE_CEKMEKOY_ALTUNIZADE = "cekmekoy-altunizade";
	
	public String route;
	
	public String departureLocation;
	public String destinationLocation;
	
	public String[] departureHours;
	
	public static boolean hasRoute(String route) {
		return route.equals(ROUTE_ALTUNIZADE_CEKMEKOY)
				|| route.equals(ROUTE_CEKMEKOY_ALTUNIZADE);
	}

}
