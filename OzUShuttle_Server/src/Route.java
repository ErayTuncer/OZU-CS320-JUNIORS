
public class Route {

	public static final String CEKMEKOY_TO_ALTUNIZADE = "cekmekoy-altunizade";
	public static final String ALTUNIZADE_TO_CEKMEKOY = "altunizade-cekmekoy";

	public static final String CEKMEKOY_TO_NISANTEPE = "cekmekoy-nisantepe";

	public static final String CEKMEKOY_TO_BOSTANCI = "cekmekoy-bostanci";
	public static final String BOSTANCI_TO_CEKMEKOY = "bostanci-cekmekoy";

	public static final String CEKMEKOY_TO_KADIKOY = "cekmekoy-kadikoy";
	public static final String KADIKOY_TO_CEKMEKOY = "kadikoy-cekmekoy";

	public static final String CEKMEKOY_TO_UMRANIYE = "cekmekoy-umraniye";
	public static final String UMRANIYE_TO_CEKMEKOY = "umraniye-cekmekoy";

	public static final String CEKMEKOY_TO_TAKSIM = "cekmekoy-taksim";
	public static final String TAKSIM_TO_CEKMEKOY = "taksim-cekmekoy";
	

	public String departure, destination, name, link;

	public Route(String route) {
		if (hasRoute(route)) {
			this.departure = route.substring(0, route.indexOf('-'));
			this.destination = route.substring(route.indexOf('-') + 1);
			this.link = "http://1-dot-testshuttles.appspot.com/" + route + ".xml";
			this.name = route;
		} else {
			throw new RuntimeException("No such route!");
		}
	}

	// If a new Route is added, do not forget to add here too.
	public static boolean hasRoute(String route) {
		return route.equals(ALTUNIZADE_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_ALTUNIZADE)
				|| route.equals(BOSTANCI_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_BOSTANCI)
				|| route.equals(KADIKOY_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_KADIKOY)
				|| route.equals(CEKMEKOY_TO_NISANTEPE)
				|| route.equals(UMRANIYE_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_UMRANIYE)
				|| route.equals(TAKSIM_TO_CEKMEKOY)
				|| route.equals(CEKMEKOY_TO_TAKSIM);
	}

}
