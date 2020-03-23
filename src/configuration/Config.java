package configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import httpclient.GetPolicyID;
import httpclient.getsendXML;
import main.Commands;
import main.Messages;

public class Config {
	
	//will change to get these from a config or read from the command line
	public String SOIServer;
	public String SOIManager;
	public String SOIManagerPort;
	public String SOIPort;
	public String UserName;
	public String Password;
	public int PolicyPropertyCount;
	public int ActionPropertyCount;
	public Messages mess;
	Properties configFile;
		
	public Config(){
		//Have a look at this also for encryption if needed
		//https://stackoverflow.com/questions/10306673/securing-a-password-in-a-properties-file
		configFile = new java.util.Properties();
		 try {
		   InputStream conf = new FileInputStream("config.properties");
		   configFile.load(conf);
		   
		   SOIServer = this.configFile.getProperty("SOIServer");
		   SOIManager = this.configFile.getProperty("SOIManager");
		   SOIManagerPort = this.configFile.getProperty("SOIManagerPort");
		   SOIPort = this.configFile.getProperty("SOIPort");
		   UserName = this.configFile.getProperty("SOIUserName");
		   Password = this.configFile.getProperty("SOIPassword");
		   PolicyPropertyCount = Integer.parseInt(this.configFile.getProperty("PolicyPropertyCount"));
		   ActionPropertyCount = Integer.parseInt(this.configFile.getProperty("ActionPropertyCount"));
		   
		 }catch(Exception eta){
		     eta.printStackTrace();
		 }
	}
	
	private static Config instance = null;
	public static Config getInstance(){
		if(instance==null){
			instance =  new Config();
		}
		return instance;
	}
	
	public String getProperty(String key)
	   {
	 String value = this.configFile.getProperty(key);
	 return value;
	   }
}
