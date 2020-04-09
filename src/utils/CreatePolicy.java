package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import configuration.Config;
import entities.ActionPolicy;
import entities.EscalationPolicy;
import entities.EscalationPolicyAction;
import entities.EscalationPolicyService;
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
	String serviceBody;
	boolean isService = false;
	
	
	public Boolean create(EscalationPolicy escp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException, InterruptedException{
		
		String urlbody = escp.getPOSTContent();
		String name = escp.getName();
		//mAKE SURE THAT THE POLICY DOES NOT ALREADY EXIST
		String[] args = {"dummy",name};
		if(commands.getPolicy(args, false, false)){
			
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
		
		//Now check Service exists so it can be added to the policy
		if(!escp.getSOIService().isEmpty() && !(escp.getSOIService() == null) && !escp.getSOIService().equals(" ")){
			
			isService = true;
			List<String> serviceids = new ArrayList<String>();
		
			String[] services = {"dummy",escp.getSOIService()};
			String serviceid = commands.getServiceID(services, true);
			if(serviceid == null){
				logger.log(Level.WARNING,"No service found with name "+ escp.getSOIService() + " Policy "+name+" will not be created");
				return false;
			}
			serviceids.add(serviceid);
			EscalationPolicyService escPolSer = new EscalationPolicyService();
			
			escPolSer.setIds(serviceids);
			serviceBody = escPolSer.getBody();
			
		} else {
			logger.log(Level.INFO,"No Service To add");
			isService = false;
		}
		

			

		HTTPResponseData result = myaction.postxml(UserName, Password, URL, contentType, urlbody);
		
		if(result.responseCode == 201){
			
			//Now we add the actions
			
			URL = result.responseLocation.substring(0, result.responseLocation.length() - 6) +"/action";
			String URLService = result.responseLocation.substring(0, result.responseLocation.length() - 6) +"/service";
			//System.out.println(URL);
			
			//CREATE ACTION BODY
			EscalationPolicyAction escPolAct = new EscalationPolicyAction();
			escPolAct.setIds(ids);
			urlbody = escPolAct.getBody();
			
			
			result = myaction.putxml(UserName, Password, URL, contentType, urlbody);
			
			if(result.responseCode == 200){

				logger.log(Level.WARNING,"Policy Actions Added Successfully");
			} else {

				return false;
			}
			
			//Now we add the service
			if(isService){
			contentType = "application/xml";
			result = myaction.putxml(UserName, Password, URLService, contentType, serviceBody);

			if(result.responseCode == 200){

				logger.log(Level.INFO,"Policy Service Added Successfully");
				return true;
			} else {

				return false;
			}
			} else {
				
				return true;
			}
			
		} else {

			return false;	
		}
		
		
	}
	}

}
