package utils;

import entities.ActionPolicy;

public class CreateActionPolicy {
	
	public ActionPolicy createActionPolicy(String[] policy){
		
		
		ActionPolicy actpolicy = new ActionPolicy();
		
		actpolicy.setName(policy[0]);
		actpolicy.setDescription(policy[1]);
		actpolicy.setUserAttrib1(policy[2]);
		actpolicy.setUserAttrib2(policy[3]);

		
		return actpolicy;
	}

}
