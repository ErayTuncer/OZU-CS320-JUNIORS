
import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParserTest {

	@Test
	public void test() {
		Schedule sch = XMLParser.parse(Route.ALTUNIZADE_TO_CEKMEKOY);
		System.out.println(sch.weekdayHours.length);
		for (int i = 0; i < sch.weekdayHours.length; i++) {
			System.out.println(sch.weekdayHours[i]);
		}
	}

	@Test
	public void test1() throws MalformedURLException, ParserConfigurationException, IOException, SAXException{
		Document doc = XMLParser.getXMLDocument("https://spreadsheets.google.com/feeds/list/0AmK7DU4zdDcLdGFIVUdTakZWWHoyYjFVYVBwWlljWkE/od6/public/basic");
		System.out.println(doc.getNodeName());
		printContent(doc.getChildNodes(), 3);
	}

	private void printContent(NodeList nodeList, int indent) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			String format = "%" + indent + "s\n";
			System.out.printf(format, nodeList.item(i).getNodeName());
			printContent(nodeList.item(i).getChildNodes(), indent + 3);
		}
		
	}
}
