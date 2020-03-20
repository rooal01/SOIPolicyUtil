package entities;

public class ActionPolicy {
	
	String name;
	String description;
	String userAttrib1;
	String userAttrib2;
	String POSTbody;
	String endpoint;
	
		
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getUserAttrib1() {
		return userAttrib1;
	}
	public void setUserAttrib1(String userAttrib1) {
		this.userAttrib1 = userAttrib1;
	}
	public String getUserAttrib2() {
		return userAttrib2;
	}
	public void setUserAttrib2(String userAttrib2) {
		this.userAttrib2 = userAttrib2;
	}
	public String getPOSTbody() {
		
		StringBuilder body = new StringBuilder();
		body.append("<env:Envelope xmlns:ns11=\"http://ns.ca.com/2009/01/usm-data\" xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wxf=\"http://schemas.xmlsoap.org/ws/2004/09/transfer\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\" xmlns:wsman=\"http://schemas.dmtf.org/wbem/wsman/1/wsman.xsd\">");
		body.append("	<env:Header>");
		body.append("		<wsa:ReplyTo xmlns:usm=\"http://ns.ca.com/2009/07/usm-core\">");
		body.append("		<wsa:Address env:mustUnderstand=\"true\">http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address>");
		body.append("		</wsa:ReplyTo>");
		body.append("		<wsa:MessageID env:mustUnderstand=\"true\" xmlns:usm=\"http://ns.ca.com/2009/07/usm-core\">uuid:4add8974-25a0-4fc4-84f6-c7f88158f8f7</wsa:MessageID>");
		body.append("		<wsa:To xmlns:ns14=\"http://ns.ca.com/2009/07/usm-core\" env:mustUnderstand=\"true\">http://samanager:7090/sam/webservice</wsa:To>");
		body.append("		<wsman:ResourceURI xmlns:usm=\"http://ns.ca.com/2009/07/usm-core\">http://ns.ca.com/2009/01/usm-data/EscalationpolicyAction</wsman:ResourceURI>");
		body.append("		<wsa:Action env:mustUnderstand=\"true\" xmlns:ns11=\"http://schemas.sam.ca.com/webservice/1/alarm.xsd\" xmlns:ns13=\"http://ns.ca.com/2009/01/usm-data\" xmlns:ns14=\"http://ns.ca.com/2009/07/usm-core\" xmlns:ns15=\"http://ns.ca.com/2009/07/eventing\">http://schemas.xmlsoap.org/ws/2004/09/transfer/Create</wsa:Action>");
		body.append("	</env:Header>");
		body.append("	<env:Body>");
		body.append("		<ns11:EscalationpolicyAction>");
		body.append("			<sam_action_type>7</sam_action_type>");
		body.append("            <sam_action_enabled>True</sam_action_enabled>");
		body.append("			<sam_action_propertyset_id_1>1</sam_action_propertyset_id_1>");
		body.append("			<sam_action_propertyset_xml_1>&amp;lt;propertyset type=&amp;quot;default&amp;quot;&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76545&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib1+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76546&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib2+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;/propertyset&amp;gt;</sam_action_propertyset_xml_1>");
		body.append("			<ns11:item_name>"+name+"</ns11:item_name>");
		body.append("			<ns11:item_description>"+description+"</ns11:item_description>");
		body.append("		</ns11:EscalationpolicyAction>");
		body.append("	</env:Body>");
		body.append("</env:Envelope>");
		//System.out.println(body.toString());
		return body.toString();
	}
	public void setPOSTbody(String pOSTbody) {
		POSTbody = pOSTbody;
	}
	
	
	
	
	
	

}
