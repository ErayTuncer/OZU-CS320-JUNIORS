
public class Route {
	
	public static final String ALTUNIZADE_TO_CEKMEKOY = "altunizade-cekmekoy";
	public static final String CEKMEKOY_TO_ALTUNIZADE = "cekmekoy-altunizade";
	
	public String departure, destination, name;
	public RouteLink routeLink;
	
	public Route(String route) {
		if(route.equals(ALTUNIZADE_TO_CEKMEKOY)){
			this.departure = "altunizade";
			this.destination = "cekmekoy";
		} else if (route.equals(CEKMEKOY_TO_ALTUNIZADE)) {
			this.departure = "cekmekoy";
			this.destination = "altunizade";
		} else {
			//TODO:
		}
		this.routeLink = new RouteLink(route);
		this.name = route;
	}
	
	public static boolean hasRoute(String route) {
		return route.equals(ALTUNIZADE_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_ALTUNIZADE);
	}
	
}
