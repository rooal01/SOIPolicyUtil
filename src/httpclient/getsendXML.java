package httpclient;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import httpclient.HTTPResponseData;
import httpclient.HttpClientBase;

public class getsendXML {
	
	public HTTPResponseData getxml(final String username, final String password, String url, String contentType)
			throws SAXException, IOException, ParserConfigurationException {
		HttpClientBase getPolicies = new HttpClientBase();
		HTTPResponseData result = getPolicies.openGetConnection(username, password, url, contentType);
		if(result.responseCode == 200) {
			
			
		} else{
			System.out.println("Error getting policies");
		}
		
		return result;
	}
	
	
	public HTTPResponseData postxml(final String username, final String password, String url, String contentType, String policy)
			throws SAXException, IOException, ParserConfigurationException {
		HttpClientBase getPolicies = new HttpClientBase();
		HTTPResponseData result = getPolicies.openPostConnection(username, password, url, contentType, policy);
		if(result.responseCode == 200) {
			
			
		} else{
			System.out.println("Error getting policies");
		}
		
		return result;
	}
	
	public HTTPResponseData putxml(final String username, final String password, String url, String contentType, String policy)
			throws SAXException, IOException, ParserConfigurationException {
		HttpClientBase getPolicies = new HttpClientBase();
		HTTPResponseData result = getPolicies.openPutConnection(username, password, url, contentType, policy);
		if(result.responseCode == 200) {
			
			
		} else{
			System.out.println("Error getting policies");
		}
		
		return result;
	}

}
