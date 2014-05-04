
public abstract class Route {
	
	public static final String ALTUNIZADE_TO_CEKMEKOY = "altunizade-cekmekoy";
	public static final String CEKMEKOY_TO_ALTUNIZADE = "cekmekoy-altunizade";
	
	public static boolean hasRoute(String route) {
		return route.equals(ALTUNIZADE_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_ALTUNIZADE);
	}

}
