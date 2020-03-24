package entities;

public class ActionPolicy {
	
	String name;
	String description;
	String userAttrib4;
	String userAttrib5;
	String userAttrib6;
	String userAttrib7;
	String userAttrib8;
	String userAttrib9;
	String userAttrib10;
	
	String POSTbody;
	String endpoint;
	
	
	public String getUserAttrib4() {
		return userAttrib4;
	}
	public void setUserAttrib4(String userAttrib4) {
		this.userAttrib4 = userAttrib4;
	}
	public String getUserAttrib5() {
		return userAttrib5;
	}
	public void setUserAttrib5(String userAttrib5) {
		this.userAttrib5 = userAttrib5;
	}
	public String getUserAttrib6() {
		return userAttrib6;
	}
	public void setUserAttrib6(String userAttrib6) {
		this.userAttrib6 = userAttrib6;
	}
	public String getUserAttrib7() {
		return userAttrib7;
	}
	public void setUserAttrib7(String userAttrib7) {
		this.userAttrib7 = userAttrib7;
	}
	public String getUserAttrib8() {
		return userAttrib8;
	}
	public void setUserAttrib8(String userAttrib8) {
		this.userAttrib8 = userAttrib8;
	}
	public String getUserAttrib9() {
		return userAttrib9;
	}
	public void setUserAttrib9(String userAttrib9) {
		this.userAttrib9 = userAttrib9;
	}
	public String getUserAttrib10() {
		return userAttrib10;
	}
	public void setUserAttrib10(String userAttrib10) {
		this.userAttrib10 = userAttrib10;
	}
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
		body.append("			<sam_action_propertyset_xml_1>&amp;lt;propertyset type=&amp;quot;default&amp;quot;&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76548&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib4+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76549&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib5+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76593&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib6+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76594&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib7+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76595&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib8+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76596&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib9+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;property create=&amp;quot;false&amp;quot;&amp;gt;&amp;lt;name&amp;gt;76597&amp;lt;/name&amp;gt;&amp;lt;value&amp;gt;&amp;lt;![CDATA["+userAttrib10+"]]&amp;gt;&amp;lt;/value&amp;gt;&amp;lt;/property&amp;gt;&amp;lt;/propertyset&amp;gt;</sam_action_propertyset_xml_1>");
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
