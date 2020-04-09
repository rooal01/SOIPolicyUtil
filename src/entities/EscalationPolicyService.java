package entities;

import java.util.ArrayList;
import java.util.List;

public class EscalationPolicyService {
	
	
	List<String> ids = new ArrayList<String>();
	String body;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getBody() {
		
		StringBuilder abody = new StringBuilder();
		abody.append("<ids>");
		for (int i = 0; i < ids.size(); i++) {
			abody.append("<id>");
			abody.append(ids.get(i));
			abody.append("</id>");	
		}
		abody.append("</ids>");
		
		return abody.toString();
	}

	public void setBody(String body) {
		this.body = body;
	}
	

}
