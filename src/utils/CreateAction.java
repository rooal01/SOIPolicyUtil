package utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import configuration.Config;
import entities.ActionPolicy;
import httpclient.HTTPResponseData;

public class CreateAction {
	
	Config config = configuration.Config.getInstance();
	static Logger logger = Logger.getLogger(CreateAction.class.getName());
	
	public Boolean create(ActionPolicy actp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException{
		
		
		String urlbody = actp.getPOSTbody();
		String name = actp.getName();
		String[] args = {"dummy",name};
		
		if(config.commands.getAction(args, false)){
			
			logger.log(Level.WARNING,"Action already exists. Name = "+ name);
			return false;
		} else {

		
		//Check if policy already exists
		
		
		HTTPResponseData result = config.myaction.postxml(UserName, Password, URL, contentType, urlbody);
		
		if(result.responseCode == 200){
			return true;
		} else {
			return false;	
		}
		
		
	}
	}
}
