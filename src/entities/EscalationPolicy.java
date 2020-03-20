package entities;

import java.util.ArrayList;
import java.util.List;

public class EscalationPolicy {
	
	String name;
	String scheduleType = "ALWAYS";
	String description;
	Boolean enabled = true;
	Boolean global = false;
	Boolean serviceAlert = true;
	Boolean infrastructureAlert = true;
	Boolean infrastructureSymptomAlert = false;
	Boolean infrastructureRootCauseAlert = false;
	Boolean maintenanceCIMode = true;
	Boolean maintenanceServiceMode = false;
	Boolean advancedType = false;
	String POSTContent;
	String containsString;
	String endpoint;
	
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	List<String> ActionPolicy = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContainsString() {
		return containsString;
	}
	public void setContainsString(String containsString) {
		this.containsString = containsString;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getInfrastructureAlert() {
		return infrastructureAlert;
	}
	public void setInfrastructureAlert(Boolean infrastructureAlert) {
		this.infrastructureAlert = infrastructureAlert;
	}
	public List<String> getActionPolicy() {
		return ActionPolicy;
	}
	public void setActionPolicy(List<String>actionPolicy) {
		ActionPolicy = actionPolicy;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getGlobal() {
		return global;
	}
	public void setGlobal(Boolean global) {
		this.global = global;
	}
	public Boolean getServiceAlert() {
		return serviceAlert;
	}
	public void setServiceAlert(Boolean serviceAlert) {
		this.serviceAlert = serviceAlert;
	}
	public Boolean getInfrastrictureAlert() {
		return infrastructureAlert;
	}
	public void setInfrastrictureAlert(Boolean infrastrictureAlert) {
		this.infrastructureAlert = infrastrictureAlert;
	}
	public Boolean getInfrastructureSymptomAlert() {
		return infrastructureSymptomAlert;
	}
	public void setInfrastructureSymptomAlert(Boolean infrastructureSymptomAlert) {
		this.infrastructureSymptomAlert = infrastructureSymptomAlert;
	}
	public Boolean getInfrastructureRootCauseAlert() {
		return infrastructureRootCauseAlert;
	}
	public void setInfrastructureRootCauseAlert(Boolean infrastructureRootCauseAlert) {
		this.infrastructureRootCauseAlert = infrastructureRootCauseAlert;
	}
	public Boolean getMaintenanceCIMode() {
		return maintenanceCIMode;
	}
	public void setMaintenanceCIMode(Boolean maintenanceCIMode) {
		this.maintenanceCIMode = maintenanceCIMode;
	}
	public Boolean getMaintenanceServiceMode() {
		return maintenanceServiceMode;
	}
	public void setMaintenanceServiceMode(Boolean maintenanceServiceMode) {
		this.maintenanceServiceMode = maintenanceServiceMode;
	}
	public Boolean getAdvancedType() {
		return advancedType;
	}
	public void setAdvancedType(Boolean advancedType) {
		this.advancedType = advancedType;
	}
	public String getPOSTContent() {
		StringBuilder body = new StringBuilder();
		body.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		body.append("<escalationPolicyDefinition>");
		body.append("<name>"+name+"</name>");
		body.append("<scheduleType>ALWAYS</scheduleType>");
		body.append("<description>"+description+"</description>");
		body.append("<criteria>");
		body.append("&lt;esc-policy&gt;");
		body.append("&lt;attr-filter&gt;");
		body.append("&lt;and&gt;");
		body.append("&lt;has-substring-ignore-case&gt;");
		body.append("&lt;attribute id=\"0x12948\"&gt;");
		body.append("&lt;value&gt;"+containsString+"&lt;/value&gt;");
		body.append("&lt;/attribute&gt;");
		body.append("&lt;/has-substring-ignore-case&gt;");
		body.append("&lt;/and&gt;");
		body.append("&lt;/attr-filter&gt;");
		body.append("&lt;/esc-policy&gt;");
		body.append("</criteria>");
		body.append("<enabled>"+enabled+"</enabled>");
		body.append("<global>"+global+"</global>");
		body.append("<serviceAlert>"+serviceAlert+"</serviceAlert>");
		body.append("<infrastructureAlert>"+infrastructureAlert+"</infrastructureAlert>");
		body.append("<infrastructureSymptomAlert>"+infrastructureSymptomAlert+"</infrastructureSymptomAlert>");
		body.append("<infrastructureRootCauseAlert>"+infrastructureRootCauseAlert+"</infrastructureRootCauseAlert>");
		body.append("<maintenanceCIMode>"+maintenanceCIMode+"</maintenanceCIMode>");
		body.append("<maintenanceServiceMode>"+maintenanceServiceMode+"</maintenanceServiceMode>");
		body.append("<advancedType>"+advancedType+"</advancedType>");
		body.append("</escalationPolicyDefinition>");
		
		return body.toString();
	}

	
	
	
	
	

}
