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
	public void checkRoute5() {
		Route route = new Route("cekmekoy-nisantepe");
		assertEquals(route.destination, "nisantepe");
	}

	@Test
	public void checkRoute6() {
		Route route = new Route("cekmekoy-bostanci");
		assertEquals(route.destination, "bostanci");
	}

	@Test
	public void checkRoute7() {
		Route route = new Route("cekmekoy-kadikoy");
		assertEquals(route.destination, "kadikoy");
	}

	@Test
	public void checkRoute8() {
		Route route = new Route("kadikoy-cekmekoy");
		assertEquals(route.destination, "cekmekoy");
	}

	@Test
	public void checkRoute9() {
		Route route = new Route("cekmekoy-umraniye");
		assertEquals(route.destination, "umraniye");
	}

	@Test
	public void checkRoute10() {
		Route route = new Route("umraniye-cekmekoy");
		assertEquals(route.destination, "cekmekoy");
	}

	@Test
	public void checkRoute11() {
		Route route = new Route("bostanci-cekmekoy");
		assertEquals(route.destination, "cekmekoy");
	}

	@Test
	public void checkRoute12() {
		Route route = new Route("cekmekoy-taksim");
		assertEquals(route.destination, "taksim");
	}

	@Test
	public void checkRoute13() {
		Route route = new Route("taksim-cekmekoy");
		assertEquals(route.destination, "cekmekoy");
	}

	@Test
	public void checkhasRoute1() {
		Boolean b = Route.hasRoute("altunizade-cekmekoy");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute2() {
		Boolean b = Route.hasRoute("cekmekoy-altunizade");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute3() {
		Boolean b = Route.hasRoute("cekmekoy-nisantepe");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute4() {
		Boolean b = Route.hasRoute("cekmekoy-bostanci");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute5() {
		Boolean b = Route.hasRoute("bostanci-cekmekoy");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute6() {
		Boolean b = Route.hasRoute("cekmekoy-kadikoy");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute7() {
		Boolean b = Route.hasRoute("kadikoy-cekmekoy");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute8() {
		Boolean b = Route.hasRoute("cekmekoy-umraniye");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute9() {
		Boolean b = Route.hasRoute("umraniye-cekmekoy");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute10() {
		Boolean b = Route.hasRoute("cekmekoy-taksim");
		assertTrue(b);
	}

	@Test
	public void checkhasRoute11() {
		Boolean b = Route.hasRoute("taksim-cekmekoy");
		assertTrue(b);
	}

}
