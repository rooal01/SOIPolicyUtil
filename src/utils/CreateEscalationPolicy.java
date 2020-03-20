package utils;

import java.util.ArrayList;
import java.util.List;

import entities.ActionPolicy;
import entities.EscalationPolicy;

public class CreateEscalationPolicy {
	
public EscalationPolicy createEscalationPolicy(String[] policy){
		
		
	EscalationPolicy escpolicy = new EscalationPolicy();
		
	    int count = policy.length - 3;// get the amount of actions to be added
	    List<String> actions= new ArrayList<String>();
	    for (int i = 0; i < count; i++) {
	    	actions.add(policy[i+3]);
		}

		escpolicy.setName(policy[0]);
		escpolicy.setDescription(policy[1]);
		escpolicy.setContainsString(policy[2]);
		escpolicy.setActionPolicy(actions); //This is the name of the action policy to add. Might be a number so this could be an array
		//TODO the rest of the properties

		
		return escpolicy;
	}

}
