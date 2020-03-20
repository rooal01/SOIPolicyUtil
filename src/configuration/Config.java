package configuration;

import httpclient.GetPolicyID;
import httpclient.getsendXML;
import main.Commands;
import main.Messages;

public class Config {
	
	//will change to get these from a config or read from the command line
	public String SOIServer = "sauiserver";
	public String SOIManager = "samanager";
	public String SOIManagerPort = "7090";
	public String SOIPort = "7070";
	public String UserName ="alan";
	public String Password = "Password1";
	public Messages mess;
	public getsendXML myaction = new getsendXML();
	public GetPolicyID getPolicyID = new GetPolicyID();
	public Commands commands = new Commands();
	
	public Config(){
		
	}
	
	private static Config instance = null;
	public static Config getInstance(){
		if(instance==null){
			instance =  new Config();
		}
		return instance;
	}
}
