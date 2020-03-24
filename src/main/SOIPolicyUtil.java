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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import configuration.MyLogger;
import configuration.Commandline;
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
	static CommandLine cmd;
	
	public static void main(String[] args) throws SAXException, IOException, ParseException, ParserConfigurationException {
		
		Commands commands = new Commands();
		//get logging setting
		mylogger.setLogging();
		Commandline cmdlineopt = new Commandline();
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		
		try {
			cmd = parser.parse( Commandline.cmdopts(), args);
		} catch (ParseException e) {
			System.out.println("Invalid Command line Parameter passed");
			formatter.printHelp("SOIPolicyUtil", Commandline.cmdopts());
			System.exit(1);
		}

		
						
		// TODO Auto-generated method stub
		//Read input to determine which command has been run
		Messages mess = new Messages();
		if(args.length < 2){
			formatter.printHelp("SOIPolicyUtil", Commandline.cmdopts());
			System.exit(1);
		}
		

		  if(cmd.hasOption("getAction")){
			  
			logger.log(Level.FINE, "getAction Requested");
		    commands.getAction(args, true, true);
		    
		  } else if(cmd.hasOption("getPolicy")){
			  
			  logger.log(Level.FINE, "getPolicy Requested");
			  commands.getPolicy(args, true, true);
			  
		  } else if(cmd.hasOption("createPolicy")){

			  logger.log(Level.FINE, "createPolicy Requested");
			  commands.createPolicy(args);
			  
		  }else if(cmd.hasOption("createAction")){

			  logger.log(Level.FINE, "createAction Requested");
			  commands.createAction(args);
		  }
		  else {
			  
			  formatter.printHelp("SOIPolicyUtil", Commandline.cmdopts());
		}

	}
	


		
		
}
