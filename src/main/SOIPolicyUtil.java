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

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
	
	static String SOIServer = "sauiserver";
	static String SOIManager = "samanager";
	static String SOIManagerPort = "7090";
	static String SOIPort = "7070";
	static String UserName ="alan";
	static String Password = "Password1";	
	Messages mess;
	getsendXML myaction = new getsendXML();
	GetPolicyID getPolicyID = new GetPolicyID();
	static SOIPolicyUtil util = new SOIPolicyUtil();

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		//Read input to determine which command has been run
		Messages mess = new Messages();
		if(args.length < 2){
			System.out.println(mess.InvalidParam);
			System.exit(1);
		}
		
		switch (args[0]) {
		  case "getAction":
		    System.out.println("getAction requested");
		    util.getAction(args);
		    break;
		  case "getPolicy":
		    System.out.println("getPolicyRequested");
		    util.getPolicy(args);
		    break;
		  case "createPolicy":
			    System.out.println("CreatePolicy requested");
			    util.createPolicy(args);
			    break;
		  case "createAction":
			    System.out.println("createAction Requested");
			    util.createAction(args);
			    break;
		  default:
		    System.out.println(mess.InvalidParam);
		}

	}
	

	public boolean getAction(String[] args) throws SAXException, IOException, ParserConfigurationException{
		//policy filter name is the second parameter
    HTTPResponseData result;	
	String actionFilter = args[1];
	String ContentType = "application/atom+xml";
	String ActionURL = "http://"+SOIServer+":"+SOIPort+"/rest/escalationPolicyAction?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
	result = myaction.getxml(UserName, Password, ActionURL, ContentType);
	Document doc = processDom(result.responseBody);
	String policydetails = getPolicyID.getDom(doc, actionFilter);
	
	if(policydetails == null){
		System.out.print(mess.NoPolicy + actionFilter);
		return false;
	}
	ContentType = "application/xml";
	
	result = myaction.getxml(UserName, Password, policydetails, ContentType);
	
	if(result.responseCode == 200){
		
		
		System.out.print(result.responseBody);
		return true;
	}
	return false;
		
	}
	
	
	public String getActionID(String[] args) throws SAXException, IOException, ParserConfigurationException{
		//policy filter name is the second parameter
    HTTPResponseData result;	
	String actionFilter = args[1];
	String ContentType = "application/atom+xml";
	String ActionURL = "http://"+SOIServer+":"+SOIPort+"/rest/escalationPolicyAction?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
	result = myaction.getxml(UserName, Password, ActionURL, ContentType);
	Document doc = processDom(result.responseBody);
	String policydetails = getPolicyID.getDom(doc, actionFilter);
	
	if(policydetails == null){
		System.out.print(mess.NoPolicy + actionFilter);
		return null;
	}

//TODO get right number
	return policydetails+"/entry";
		
	}
	
	public boolean getPolicy(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		HTTPResponseData result;
		String policydetails =  null;
		String actionFilter = args[1];
		String ContentType = "application/atom+xml";
		String ActionURL = "http://"+SOIServer+":"+SOIPort+"/rest/escalationPolicy?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
		result = myaction.getxml(UserName, Password, ActionURL, ContentType);
		Document doc = processDom(result.responseBody);
		policydetails = getPolicyID.getDom(doc, actionFilter);
		
		
		if(policydetails == null){
			System.out.print(mess.NoPolicy + actionFilter);
			return false;
		}
		ContentType = "application/xml";
		result = myaction.getxml(UserName, Password, policydetails, ContentType);
		
		if(result.responseCode == 200){
			
			System.out.print(result.responseBody);
			return true;
		}
		return false;
	}

	public void createAction(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		String filename = args[1];
		int actionParmCount = 4;
		ProcessFile pfile = new ProcessFile();
		CreateActionPolicy createActionP = new CreateActionPolicy();
		CreateAction createAction = new CreateAction();
		List<ActionPolicy> allActionPolicies = new ArrayList<ActionPolicy>();
		
		
		List<String[]> allActions = pfile.processFile(filename,  actionParmCount);
		
		System.out.println("Processing " + allActions.size() + " actions");
		
		Iterator<String[]> actionIterator = allActions.iterator();
        while (actionIterator.hasNext()) {
            String[] action = actionIterator.next();
            if(!(action == null)){
            	allActionPolicies.add(createActionP.createActionPolicy(action));	
            }
            
        }

        //Need to check that the action does not already exist. TODO
        boolean result;
        String ActionURL = "http://"+SOIManager+":"+SOIManagerPort+"/sam/webservice";
        String contentType = "application/soap+xml;charset=UTF-8";
        Iterator<ActionPolicy> actionpIterator = allActionPolicies.iterator();
        while (actionpIterator.hasNext()) {
            ActionPolicy actionp = actionpIterator.next();
         result =  createAction.create(actionp, ActionURL, contentType, UserName, Password);
            if(result){
            	System.out.println("Success creating Action: " + actionp.getName());	
            } else { 
            	System.out.println("Failed to create Action: " + actionp.getName());	
            }
            
        }
		

		
	}
	
public void createPolicy(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		String filename = args[1];
		int actionParmCount = 4;
		ProcessFile pfile = new ProcessFile();
		CreateEscalationPolicy createEscalationP = new CreateEscalationPolicy();
		CreatePolicy createPolicy = new CreatePolicy();
		List<EscalationPolicy> allEscalationPolicies = new ArrayList<EscalationPolicy>();
		
		
		List<String[]> allPolicies = pfile.processFile(filename,  actionParmCount);
		
		System.out.println("Processing " + allPolicies.size() + " escalation policies");
		
		Iterator<String[]> policyIterator = allPolicies.iterator();
        while (policyIterator.hasNext()) {
            String[] policy = policyIterator.next();
            if(!(policy == null)){
            	allEscalationPolicies.add(createEscalationP.createEscalationPolicy(policy));	
            }
            
        }
        System.out.println("STEP1");
        //Need to check that the action does not already exist. TODO
        boolean result;
        String PolicyURL = "http://"+SOIServer+":"+SOIPort+"/rest/escalationPolicy";
        String contentType = "application/xml";
        Iterator<EscalationPolicy> escalationpIterator = allEscalationPolicies.iterator();
        while (escalationpIterator.hasNext()) {
            EscalationPolicy actionp = escalationpIterator.next();
         result =  createPolicy.create(actionp, PolicyURL, contentType, UserName, Password);
            if(result){
            	System.out.println("Success creating Escalation Policy: " + actionp.getName());	
            } else { 
            	System.out.println("Failed to create Escalation Policy: " + actionp.getName());	
            }
            
        }
		

		
	}
	
	private static Document processDom(String result) throws SAXException, IOException, ParserConfigurationException {
		GetDOM processdom = new GetDOM();
		return processdom.createDom(result);
	}
	

		
		
}