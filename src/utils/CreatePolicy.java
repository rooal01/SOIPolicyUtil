package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import configuration.Config;
import entities.ActionPolicy;
import entities.EscalationPolicy;
import entities.EscalationPolicyAction;
import httpclient.GetPolicyID;
import httpclient.HTTPResponseData;
import httpclient.getsendXML;
import main.Commands;
import main.SOIPolicyUtil;

public class CreatePolicy {
	
	Config config = configuration.Config.getInstance();
	static Logger logger = Logger.getLogger(CreatePolicy.class.getName());
	public Commands commands = new Commands();
	getsendXML myaction = new getsendXML();
	GetPolicyID getPolicyID = new GetPolicyID();
	
	
	public Boolean create(EscalationPolicy escp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException{
		
		String urlbody = escp.getPOSTContent();
		String name = escp.getName();
		//mAKE SURE THAT THE POLICY DOES NOT ALREADY EXIST
		String[] args = {"dummy",name};
		if(commands.getPolicy(args, false)){
			
			logger.log(Level.WARNING,"Escalation Policy already exists. Name = "+ name);
			return false;
		} else {

		//Make Sure the action for the policy exists and get its ID
		List<String> actions = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		actions = escp.getActionPolicy();
		for (int i = 0; i < actions.size(); i++) {
			String[] tempact = {"dummy",actions.get(i)};
			String actionid = commands.getActionID(tempact, true);
			if(actionid == null){
				logger.log(Level.WARNING,"No action found with name "+ actions.get(i) + " Policy "+name+" will not be created");
				return false;
			}
			ids.add(actionid);
		}
		


			

		HTTPResponseData result = myaction.postxml(UserName, Password, URL, contentType, urlbody);
		
		if(result.responseCode == 201){
			
			URL = result.responseLocation.substring(0, result.responseLocation.length() - 6) +"/action";
			//System.out.println(URL);
			
			//CREATE ACTION BODY
			EscalationPolicyAction escPolAct = new EscalationPolicyAction();
			escPolAct.setIds(ids);
			urlbody = escPolAct.getBody();
			

			result = myaction.putxml(UserName, Password, URL, contentType, urlbody);
			
			if(result.responseCode == 200){

				return true;
			} else {

				return false;
			}

		} else {

			return false;	
		}
		
		
	}
	}

}
