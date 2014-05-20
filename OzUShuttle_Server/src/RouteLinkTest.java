import static org.junit.Assert.*;

import org.junit.Test;


public class RouteLinkTest {

	@Test
	public void checkRoute1() {
		RouteLink routeLink = new RouteLink(Route.ALTUNIZADE_TO_CEKMEKOY);
		assertEquals("https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic", routeLink.link);

	}

	@Test
	public void checkRoute2() {
		RouteLink routeLink = new RouteLink(Route.CEKMEKOY_TO_ALTUNIZADE);
		assertEquals("https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic", routeLink.link);

	}

	@Test
	public void checkRoute3() {
		RouteLink routeLink = new RouteLink(Route.ALTUNIZADE_TO_CEKMEKOY);
		assertEquals(1, routeLink.timeIndex);

	}

	@Test
	public void checkRoute4() {
		RouteLink routeLink = new RouteLink(Route.CEKMEKOY_TO_ALTUNIZADE);
		assertEquals(2, routeLink.timeIndex);

	}

}
