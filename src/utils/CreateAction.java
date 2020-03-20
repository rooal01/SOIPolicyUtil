package utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import configuration.Config;
import entities.ActionPolicy;
import httpclient.GetPolicyID;
import httpclient.HTTPResponseData;
import httpclient.getsendXML;
import main.Commands;

public class CreateAction {
	
	Config config = configuration.Config.getInstance();
	static Logger logger = Logger.getLogger(CreateAction.class.getName());
	public Commands commands = new Commands();
	getsendXML myaction = new getsendXML();

	
	public Boolean create(ActionPolicy actp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException{
		
		
		String urlbody = actp.getPOSTbody();
		String name = actp.getName();
		String[] args = {"dummy",name};
		
		if(commands.getAction(args, false)){
			
			logger.log(Level.WARNING,"Action already exists. Name = "+ name);
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
