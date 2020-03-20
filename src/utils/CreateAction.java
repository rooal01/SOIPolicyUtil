package utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entities.ActionPolicy;
import httpclient.HTTPResponseData;
import httpclient.getsendXML;
import main.SOIPolicyUtil;

public class CreateAction {
	
	getsendXML myaction = new getsendXML();
	SOIPolicyUtil policyUtil = new SOIPolicyUtil();
	
	public Boolean create(ActionPolicy actp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException{
		
		
		String urlbody = actp.getPOSTbody();
		String name = actp.getName();
		String[] args = {"dummy",name};
		
		if(policyUtil.getAction(args)){
			
			System.out.println("Action already exists. Name = "+ name);
			return false;
		} else {

		
		//Check if policy already exists
		
		
		HTTPResponseData result = myaction.postxml(UserName, Password, URL, contentType, urlbody);
		
		if(result.responseCode == 200){
			return true;
		} else {
			return false;	
		}
		
		
	}
	}
}
