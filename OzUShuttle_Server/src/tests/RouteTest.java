package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import server.Route;

public class RouteTest {

	@Test
	public void checkRoute1() {
		Route route = new Route("altunizade-cekmekoy");
		assertEquals(route.departure, "altunizade");
	}
	
	@Test
	public void checkRoute2() {
		Route route = new Route("altunizade-cekmekoy");
		assertEquals(route.destination, "cekmekoy");
	}
	
	@Test
	public void checkRoute3() {
		Route route = new Route("cekmekoy-altunizade");
		assertEquals(route.departure, "cekmekoy");
	}
	
	@Test
	public void checkRoute4() {
		Route route = new Route("cekmekoy-altunizade");
		assertEquals(route.destination, "altunizade");
	}
	
	@Test
	public void checkhasRoute1() {
		Boolean b = Route.hasRoute("altunizade-cekmekoy");
		assertEquals(true, b);
	}

	@Test
	public void checkhasRoute2() {
		Boolean b = Route.hasRoute("cekmekoy-altunizade");
		assertEquals(true, b);
	}

}
