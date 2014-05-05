import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ScheduleParser {
		
	public static Schedule parse(String route) { 
		if (route.equals(Route.ALTUNIZADE_TO_CEKMEKOY)) {
			return getAltunizadeToCekmekoy();
		}
		return null;
	}

	private static Schedule getAltunizadeToCekmekoy() {
		try {
			return readSchedule(Route.ALTUNIZADE_TO_CEKMEKOY, 2, "altunizade", "cekmekoy","https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic");
		} catch (XPathExpressionException e) {
			System.err.println("Parser XPathExpressionException!");
		} catch (MalformedURLException e) {
			System.err.println("Parser MalformedURLException!");
		} catch (ParserConfigurationException e) {
			System.err.println("Parser ParserConfigurationException!");
		} catch (IOException e) {
			System.err.println("Parser IOException!");
		} catch (SAXException e) {
			System.err.println("Parser SAXException!");
		}
		return null;
	}
	
	private static Schedule readSchedule(String route, int timeIndex, String departure, String destination, String url) throws XPathExpressionException, MalformedURLException, ParserConfigurationException, IOException, SAXException {
		Document xmlDocument = getXMLDocument(url);
		XPath xPath =  XPathFactory.newInstance().newXPath();
	
		Schedule schedule = new Schedule();
		schedule.route = route;
		schedule.departureLocation = departure;
		schedule.destinationLocation = destination;
		fillWeekdays(schedule, timeIndex, xPath, xmlDocument);
		fillWeekends(schedule, timeIndex, xPath, xmlDocument);
		fillHolidays(schedule, timeIndex, xPath, xmlDocument);
		
		return schedule;
	}

	private static void fillWeekdays(Schedule schedule, int timeIndex, XPath xPath,
			Document xmlDocument) throws XPathExpressionException {
		String expression = "/feed/entry[title='HAFTA ÝÇÝ  WEEKDAYS']/content";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		schedule.weekdayHours = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			if (timeIndex == 1) {
				time = time.substring(8, 12);
			} else {
				time = time.substring(time.lastIndexOf(", ") + 10, time.lastIndexOf(", ") + 15);
			}
			schedule.weekdayHours[i] = time;
		}
	}
	
	private static void fillWeekends(Schedule schedule, int timeIndex, XPath xPath,
			Document xmlDocument) throws XPathExpressionException {
		String expression = "/feed/entry[title='HAFTA SONU  WEEKENDS']/content";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		schedule.weekendHours = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			if (timeIndex == 1) {
				time = time.substring(8, 12);
			} else {
				time = time.substring(time.lastIndexOf(", ") + 10, time.lastIndexOf(", ") + 15);
			}
			schedule.weekendHours[i] = time;
		}
	}
	
	private static void fillHolidays(Schedule schedule, int timeIndex, XPath xPath,
			Document xmlDocument) throws XPathExpressionException {
		String expression = "/feed/entry[title='RESMÝ TATÝL OFFICIAL HOLIDAYS']/content";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		schedule.holidayHours = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			if (timeIndex == 1) {
				time = time.substring(8, 12);
			} else {
				time = time.substring(time.lastIndexOf(", ") + 10, time.lastIndexOf(", ") + 15);
			}
			schedule.holidayHours[i] = time;
		}
	}

	private static Document getXMLDocument(String url) throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		InputStream inStream = new URL(url).openStream();
		Document xmlDocument = builder.parse(inStream);
		return xmlDocument;
	}
}
