package httpclient;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
	
public class GetDOM {
	
	public Document createDom(String responsebody) throws SAXException, IOException, ParserConfigurationException{
		
//		File fXmlFile = new File("c:/java/alan.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		System.out.println("ALAN " +responsebody);
		Document doc = dBuilder.parse(new InputSource(new StringReader(responsebody)));;
				
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		
//		NodeList nList = doc.getElementsByTagName("entry");
//		
//		for (int temp = 0; temp < nList.getLength(); temp++) {
//
//			Node nNode = nList.item(temp);
//					
//			System.out.println("\nCurrent Element :" + nNode.getNodeName());
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//				Element eElement = (Element) nNode;
//
//				System.out.println("Exporting Policy:" + eElement.getElementsByTagName("title").item(0).getTextContent());
//				System.out.println("Policy ID is: " + eElement.getElementsByTagName("id").item(0).getTextContent());
//				
//			}
//			
//		}

		return doc;
		
	}
	
}
