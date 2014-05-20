import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
		return getSchedule(new Route(route));
	}

	private static Schedule getSchedule(Route route) {
		try {
			return readSchedule(route.name, route.routeLink.timeIndex, route.departure, route.destination, route.routeLink.link);
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
		System.out.println(nodeList.getLength());
		ArrayList<String> weekdayHours = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			time = pickTime(timeIndex, time);
			if(!time.equals("")) weekdayHours.add(time);
		}
		schedule.weekdayHours = getArray(weekdayHours);
	}
	
	private static void fillWeekends(Schedule schedule, int timeIndex, XPath xPath,
			Document xmlDocument) throws XPathExpressionException {
		String expression = "/feed/entry[title='HAFTA SONU  WEEKENDS']/content";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		ArrayList<String> weekendHours = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			time = pickTime(timeIndex, time);
			if(!time.equals("")) weekendHours.add(time);
		}
		schedule.weekendHours = getArray(weekendHours);
	}
	
	private static void fillHolidays(Schedule schedule, int timeIndex, XPath xPath,
			Document xmlDocument) throws XPathExpressionException {
		String expression = "/feed/entry[title='RESMÝ TATÝL OFFICIAL HOLIDAYS']/content";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		ArrayList<String> holidayHours = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			String time = nodeList.item(i).getFirstChild().getNodeValue().toString();
			time = pickTime(timeIndex, time);
			if(!time.equals("")) holidayHours.add(time);
		}
		schedule.holidayHours = getArray(holidayHours);
	}

	private static Document getXMLDocument(String url) throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		InputStream inStream = new URL(url).openStream();
		Document xmlDocument = builder.parse(inStream);
		return xmlDocument;
	}
	
	private static String pickTime(int timeIndex, String time) {
		int start = (timeIndex == 1 ? 0 : time.indexOf(','));
		int finish = (timeIndex == 1 ? time.indexOf(',') : time.length());
		String pickedTime = "";
		if(start != -1 && finish != -1) {
			time = time.substring(start, finish);
			start = time.indexOf(": ") + 1;
			finish = time.length();
			for (int i = start ; i < finish; i++) {
				if(Character.isDigit(time.charAt(i)) || time.charAt(i) == ':')
						pickedTime += time.charAt(i);
			}
			pickedTime = pickedTime.substring(0, pickedTime.indexOf(':') + 3);
		}
		return pickedTime;
	}
	
	private static String[] getArray(ArrayList<String> hoursList) {
		String[] hoursArray = new String[hoursList.size()];
		for (int i = 0; i < hoursList.size(); i++) {
			hoursArray[i] = hoursList.get(i);
		}
		return hoursArray;
	}

}
