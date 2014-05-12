
public class RouteLink {

	public String link;
	public int timeIndex;
	
	public RouteLink (String route){
		if(route.equals(Route.ALTUNIZADE_TO_CEKMEKOY)) {
			this.link = "https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic";
			this.timeIndex = 1;
		} else if (route.equals(Route.CEKMEKOY_TO_ALTUNIZADE) ) {
			this.link = "https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic";
			this.timeIndex = 2;			
		} else {
			//TODO:
		}
	}
}
