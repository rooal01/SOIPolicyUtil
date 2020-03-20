package main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import configuration.MyLogger;
import configuration.Config;
import entities.ActionPolicy;
import entities.EscalationPolicy;
import httpclient.GetDOM;
import httpclient.GetPolicyID;
import httpclient.HTTPResponseData;
import httpclient.getsendXML;
import utils.CreateAction;
import utils.CreateActionPolicy;
import utils.CreateEscalationPolicy;
import utils.CreatePolicy;
import utils.ProcessFile;

public class SOIPolicyUtil {
	
	Messages mess;
	static Logger logger = Logger.getLogger(SOIPolicyUtil.class.getName());
	static MyLogger mylogger= new MyLogger();
	Config config = configuration.Config.getInstance();
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		Commands commands = new Commands();
		//get logging setting
		mylogger.setLogging();
		
				
		// TODO Auto-generated method stub
		//Read input to determine which command has been run
		Messages mess = new Messages();
		if(args.length < 2){
			logger.log(Level.INFO, mess.InvalidParam);
			System.exit(1);
		}
		
		switch (args[0]) {
		  case "getAction":
			logger.log(Level.FINE, "getAction Requested");
		    commands.getAction(args, true);
		    break;
		  case "getPolicy":
			  logger.log(Level.FINE, "getPolicy Requested");
			  commands .getPolicy(args, true);
		    break;
		  case "createPolicy":
			  logger.log(Level.FINE, "createPolicy Requested");
			  commands .createPolicy(args);
			    break;
		  case "createAction":
			  logger.log(Level.FINE, "createAction Requested");
			  commands .createAction(args);
			    break;
		  default:
			  logger.log(Level.FINE, mess.InvalidParam);
		}

	}
	


		
		
}
