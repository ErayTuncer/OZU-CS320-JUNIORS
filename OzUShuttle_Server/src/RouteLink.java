
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
		} else if (route.equals(Route.CEKMEKOY_TO_NISANTEPE)) {
			this.link = "https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od7/public/basic";
			this.timeIndex = 1;
		} else if (route.equals(Route.BOSTANCI_TO_CEKMEKOY)) {
			this.link = "https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/ocz/public/basic";
			this.timeIndex = 1;
		} else if (route.equals(Route.CEKMEKOY_TO_BOSTANCI)) {
			this.link = "https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/ocz/public/basic";
			this.timeIndex = 2;
		} else if (route.equals(Route.KADIKOY_TO_CEKMEKOY)) {
			this.link = "";
			this.timeIndex = 1;
		} else if (route.equals(Route.CEKMEKOY_TO_KADIKOY)) {
			this.link = "";			
			this.timeIndex = 2;
		} else if (route.equals(Route.UMRANIYE_TO_CEKMEKOY)) {
			this.link = "https://dl.dropboxusercontent.com/s/r78kww42jloriue/umraniye_cekmekoy.xml?dl=1&token_hash=AAHNiAZqK7EkeLFXN0qsQdaIXrXEzbZoNq1cyLyP2nMs4A&expiry=1400535448";			
			this.timeIndex = 1;
		} else if (route.equals(Route.CEKMEKOY_TO_UMRANIYE)) {
			this.link = "https://dl.dropboxusercontent.com/s/r78kww42jloriue/umraniye_cekmekoy.xml?dl=1&token_hash=AAHNiAZqK7EkeLFXN0qsQdaIXrXEzbZoNq1cyLyP2nMs4A&expiry=1400535448";
			this.timeIndex = 2;
		} else if (route.equals(Route.TAKSIM_TO_CEKMEKOY)) {
			this.link = "";
			this.timeIndex = 1;
		} else if (route.equals(Route.CEKMEKOY_TO_TAKSIM)) {
			this.link = "";
			this.timeIndex = 2;			
		} else {
			//TODO:
		}
	}
}
