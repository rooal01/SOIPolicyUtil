package utils;

import java.util.logging.Logger;

import entities.ActionPolicy;


public class CreateActionPolicy {
	static Logger logger = Logger.getLogger(CreateActionPolicy.class.getName());
	
	public ActionPolicy createActionPolicy(String[] policy){
		
		
		ActionPolicy actpolicy = new ActionPolicy();
		
		actpolicy.setName(policy[0]);
		actpolicy.setDescription(policy[1]);
		actpolicy.setUserAttrib4(policy[2]);
		actpolicy.setUserAttrib5(policy[3]);
		actpolicy.setUserAttrib6(policy[4]);
		actpolicy.setUserAttrib7(policy[5]);
		actpolicy.setUserAttrib8(policy[6]);
		actpolicy.setUserAttrib9(policy[7]);
		actpolicy.setUserAttrib10(policy[8]);

		
		return actpolicy;
	}

}
