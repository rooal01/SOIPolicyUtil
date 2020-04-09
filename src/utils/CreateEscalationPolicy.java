package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.ActionPolicy;
import entities.EscalationPolicy;

public class CreateEscalationPolicy {
	
	static Logger logger = Logger.getLogger(CreateEscalationPolicy.class.getName());
public EscalationPolicy createEscalationPolicy(String[] policy){
		
		
	EscalationPolicy escpolicy = new EscalationPolicy();
		
	    int count = policy.length - 7;// get the amount of actions to be added
	    List<String> actions= new ArrayList<String>();
	    for (int i = 0; i < count; i++) {
	    	actions.add(policy[i+7]);
		}
	    
	    escpolicy.setSOIService(policy[0]);
		escpolicy.setName(policy[1]);
		escpolicy.setDescription(policy[2]);
		escpolicy.setContainsString(policy[3]);
		escpolicy.setSourceContains(policy[4]);
		escpolicy.setUserAtt2Contains(policy[5]);
		escpolicy.setUserAttr2NotContains(policy[6]);
		escpolicy.setActionPolicy(actions); //This is the name of the action policy to add. Might be a number so this could be an array
		//TODO the rest of the properties

		
		return escpolicy;
	}

}
