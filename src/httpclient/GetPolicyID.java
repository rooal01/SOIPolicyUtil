package httpclient;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import utils.CreateAction;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
	
public class GetPolicyID {
	
	static Logger logger = Logger.getLogger(GetPolicyID.class.getName());
	
	public String getDom(Document doc, String filter, boolean expected) throws SAXException, IOException, ParserConfigurationException{
		String policyid = null;
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//		doc.getDocumentElement().normalize();
		
		Element rootElement = doc.getDocumentElement();
//		System.out.println(rootElement.getAttributeNodeNS("http://ca.com/2011/soi/rest", "totalCountHint"));
//		System.out.println("Start Extract of Policy:" + rootElement.getElementsByTagName("restApi:totalCountHint").item(0).getTextContent());
//		System.out.println("Start Extract of alan:" + rootElement.getElementsByTagName("restApi:totalCountHint").item(0).getTextContent());
		
		int numofpolicies = Integer.parseInt(rootElement.getElementsByTagName("restApi:totalCountHint").item(0).getTextContent().toString());
		
		if (numofpolicies == 0) {
			
			if(expected){
			logger.log(Level.WARNING,"No policyID found");
			}
			return policyid;
			
		} else {
			
		
		NodeList nList = doc.getElementsByTagName("entry");
		
		//Just in case we want to go through a list to find an exact match
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

			
//				System.out.println("Start Extract of Policy:" + eElement.getElementsByTagName("title").item(0).getTextContent());
//				System.out.println("Policy ID is: " + eElement.getElementsByTagName("id").item(0).getTextContent());
				
				if(eElement.getElementsByTagName("title").item(0).getTextContent().equals(filter)){
					
					policyid = eElement.getElementsByTagName("id").item(0).getTextContent().substring(0,eElement.getElementsByTagName("id").item(0).getTextContent().length() -6);
					break;
				}
				
								
			}
			
		}

		return policyid;
	}
		
	}
}
