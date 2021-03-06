package main;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import configuration.Config;
import configuration.MyLogger;
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

public class Commands {
	
	static Logger logger = Logger.getLogger(Commands.class.getName());
	static MyLogger mylogger= new MyLogger();
	Config config = configuration.Config.getInstance();
	getsendXML myaction = new getsendXML();
	GetPolicyID getPolicyID = new GetPolicyID();
	
	public boolean getAction(String[] args, boolean expected, boolean printResponse) throws SAXException, IOException, ParserConfigurationException{
		//policy filter name is the second parameter
    HTTPResponseData result;	
	String actionFilter = args[1];
	String ContentType = "application/atom+xml";
	String ActionURL = config.UIProto+"://"+config.SOIServer+":"+config.SOIPort+"/rest/escalationPolicyAction?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
	result = myaction.getxml(config.UserName, config.Password, ActionURL, ContentType);
	Document doc = processDom(result.responseBody);
	String policydetails = getPolicyID.getDom(doc, actionFilter, expected);
	
	
	if(policydetails == null){
		if(expected){
		logger.log(Level.WARNING, config.mess.NoPolicy + actionFilter);
		}
		return false;
	
	}
	ContentType = "application/xml";
	
	result = myaction.getxml(config.UserName, config.Password, policydetails, ContentType);
	
	if(result.responseCode == 200){
		if(printResponse){
			System.out.println("##################Start Response Body###########");
			System.out.println(result.responseBody);
			System.out.println("##################End Response Body###########");	
		}
		
		return true;
	}
	return false;
		
	}
	
	
	public String getActionID(String[] args, boolean expected) throws SAXException, IOException, ParserConfigurationException{
		//policy filter name is the second parameter
    HTTPResponseData result;	
	String actionFilter = args[1];
	String ContentType = "application/atom+xml";
	String ActionURL = config.UIProto+"://"+config.SOIServer+":"+config.SOIPort+"/rest/escalationPolicyAction?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
	result = myaction.getxml(config.UserName, config.Password, ActionURL, ContentType);
	Document doc = processDom(result.responseBody);
	String policydetails = getPolicyID.getDom(doc, actionFilter, expected);
	
	if(policydetails == null){
		logger.log(Level.WARNING, config.mess.NoPolicy + actionFilter);
		return null;
	}

//TODO get right number
	return policydetails+"/entry";
		
	}
	
	public String getServiceID(String[] args, boolean expected) throws SAXException, IOException, ParserConfigurationException{
		//policy filter name is the second parameter
    HTTPResponseData result;	
	String serviceFilter = args[1];
	String ContentType = "application/atom+xml";
	String ActionURL = config.UIProto+"://"+config.SOIServer+":"+config.SOIPort+"/rest/service?size=1000";
	result = myaction.getxml(config.UserName, config.Password, ActionURL, ContentType);
	
	Document doc = processDom(result.responseBody);
	String servicedetails = getPolicyID.getDom(doc, serviceFilter, expected);
	
	if(servicedetails == null){
		logger.log(Level.WARNING, config.mess.NoService + serviceFilter);
		return null;
	}

//TODO get right number
	return servicedetails+"/entry";
		
	}
	
	public boolean getPolicy(String[] args, boolean expected, boolean printResponse) throws SAXException, IOException, ParserConfigurationException{
		
		HTTPResponseData result;
		String policydetails =  null;
		String actionFilter = args[1];
		String ContentType = "application/atom+xml";
		String ActionURL = config.UIProto+"://"+config.SOIServer+":"+config.SOIPort+"/rest/escalationPolicy?size=1&filter="+ URLEncoder.encode(actionFilter, "UTF-8");
		result = myaction.getxml(config.UserName, config.Password, ActionURL, ContentType);
		Document doc = processDom(result.responseBody);
		policydetails = getPolicyID.getDom(doc, actionFilter, expected);
		
		
		if(policydetails == null){
			if(expected){
			logger.log(Level.WARNING, config.mess.NoPolicy + actionFilter);
			}
			return false;
		}
		
		ContentType = "application/xml";
		result = myaction.getxml(config.UserName, config.Password, policydetails, ContentType);
		
		if(result.responseCode == 200){
			if(printResponse){
				System.out.println("##################Start Response Body###########");
				System.out.println(result.responseBody);
				System.out.println("##################End Response Body###########");	
			}
			return true;
		}
		return false;
	}

	public void createAction(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		String filename = args[1];
		int actionParmCount = config.ActionPropertyCount;
		ProcessFile pfile = new ProcessFile();
		CreateActionPolicy createActionP = new CreateActionPolicy();
		CreateAction createAction = new CreateAction();
		List<ActionPolicy> allActionPolicies = new ArrayList<ActionPolicy>();
		
		
		List<String[]> allActions = pfile.processFile(filename,  actionParmCount);
		
		logger.log(Level.FINE, "Processing " + allActions.size() + " actions");
		
		Iterator<String[]> actionIterator = allActions.iterator();
        while (actionIterator.hasNext()) {
            String[] action = actionIterator.next();
            if(!(action == null)){
            	allActionPolicies.add(createActionP.createActionPolicy(action));	
            }
            
        }

        //Need to check that the action does not already exist. TODO
        boolean result;
        String ActionURL = config.ManagerProto+"://"+config.SOIManager+":"+config.SOIManagerPort+"/sam/webservice";
        String contentType = "application/soap+xml;charset=UTF-8";
        Iterator<ActionPolicy> actionpIterator = allActionPolicies.iterator();
        while (actionpIterator.hasNext()) {
            ActionPolicy actionp = actionpIterator.next();
         result =  createAction.create(actionp, ActionURL, contentType, config.UserName, config.Password);
            if(result){
            	logger.log(Level.INFO, "Success creating Action: " + actionp.getName());	
            } else { 
            	logger.log(Level.INFO, "Failed to create Action: " + actionp.getName());	
            }
            
        }
		

		
	}
	
public void createPolicy(String[] args) throws SAXException, IOException, ParserConfigurationException, InterruptedException{
		
		String filename = args[1];
		int actionParmCount = config.PolicyPropertyCount;
		ProcessFile pfile = new ProcessFile();
		CreateEscalationPolicy createEscalationP = new CreateEscalationPolicy();
		CreatePolicy createPolicy = new CreatePolicy();
		List<EscalationPolicy> allEscalationPolicies = new ArrayList<EscalationPolicy>();
		
		
		List<String[]> allPolicies = pfile.processFile(filename,  actionParmCount);
		
		logger.log(Level.FINE, "Processing " + allPolicies.size() + " escalation policies");
		
		Iterator<String[]> policyIterator = allPolicies.iterator();
        while (policyIterator.hasNext()) {
            String[] policy = policyIterator.next();
            if(!(policy == null)){
            	allEscalationPolicies.add(createEscalationP.createEscalationPolicy(policy));	
            }
            
        }

        //Need to check that the action does not already exist. TODO
        boolean result;
        String PolicyURL = config.UIProto+"://"+config.SOIServer+":"+config.SOIPort+"/rest/escalationPolicy";
        String contentType = "application/xml";
        Iterator<EscalationPolicy> escalationpIterator = allEscalationPolicies.iterator();
        while (escalationpIterator.hasNext()) {
            EscalationPolicy actionp = escalationpIterator.next();
         result =  createPolicy.create(actionp, PolicyURL, contentType, config.UserName, config.Password);
            if(result){
            	logger.log(Level.INFO, "Success creating Escalation Policy: " + actionp.getName());	
            } else { 
            	logger.log(Level.INFO, "Failed to create Escalation Policy: " + actionp.getName());	
            }
            
        }
		

		
	}
	
	private static Document processDom(String result) throws SAXException, IOException, ParserConfigurationException {
		GetDOM processdom = new GetDOM();
		return processdom.createDom(result);
	}

}
