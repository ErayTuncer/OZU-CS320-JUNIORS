package server;
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


public class XMLParser {
	
	public static Schedule parse(String route) { 
		return getSchedule(new Route(route));
	}
	
	private static Schedule getSchedule(Route route) {
		try {
			return readSchedule(route);
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
	
	private static Schedule readSchedule(Route route) throws XPathExpressionException, MalformedURLException, ParserConfigurationException, IOException, SAXException {
		Document xmlDocument = getXMLDocument(route.link);
		XPath xPath =  XPathFactory.newInstance().newXPath();
		Schedule schedule = new Schedule();
		schedule.route = route.name;
		schedule.departureLocation = route.departure;
		schedule.destinationLocation = route.destination;
		
		schedule.weekdayHours = getHours("weekday", xPath, xmlDocument);
		schedule.weekendHours = getHours("weekend", xPath, xmlDocument);
		schedule.holidayHours = getHours("holiday", xPath, xmlDocument);
		
		return schedule;
	}

	public static Document getXMLDocument(String url) throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		InputStream inStream = new URL(url).openStream();
		Document xmlDocument = builder.parse(inStream);
		return xmlDocument;
	}
	
	private static String[] getHours(String nodeName, XPath xPath, Document xmlDocument) throws XPathExpressionException {
		String expression = "/route/" + nodeName + "/hour";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		String[] hours = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			String hour = nodeList.item(i).getFirstChild().getNodeValue().toString();
			hours[i] = hour;
		}
		return hours;
	}
}
