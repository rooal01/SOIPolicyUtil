package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entities.ActionPolicy;
import entities.EscalationPolicy;
import entities.EscalationPolicyAction;
import httpclient.HTTPResponseData;
import httpclient.getsendXML;
import main.SOIPolicyUtil;

public class CreatePolicy {
	
	getsendXML myaction = new getsendXML();
	SOIPolicyUtil policyUtil = new SOIPolicyUtil();
	
	public Boolean create(EscalationPolicy escp, String URL, String contentType, String UserName, String Password) throws SAXException, IOException, ParserConfigurationException{
		
		System.out.println("STEP3");
		
		String urlbody = escp.getPOSTContent();
		String name = escp.getName();
		//mAKE SURE THAT THE POLICY DOES NOT ALREADY EXIST
		String[] args = {"dummy",name};
		if(policyUtil.getPolicy(args)){
			
			System.out.println("Escalation Policy already exists. Name = "+ name);
			return false;
		} else {
			System.out.println("STEP4");
		//Make Sure the action for the policy exists and get its ID
		List<String> actions = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		actions = escp.getActionPolicy();
		for (int i = 0; i < actions.size(); i++) {
			String[] tempact = {"dummy",actions.get(i)};
			String actionid = policyUtil.getActionID(tempact);
			if(actionid == null){
				System.out.println("No action found with name "+ actions.get(i) + " Policy "+name+" will not be created");
				return false;
			}
			ids.add(actionid);
		}
		

		System.out.println("STEP5");
			

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
				System.out.println("STEP7");
				return true;
			} else {
				System.out.println("STEP8");
				return false;
			}

		} else {
			System.out.println("STEP9");
			return false;	
		}
		
		
	}
	}

}
