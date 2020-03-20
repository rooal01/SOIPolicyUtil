package configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class MyLogger {
	
	public void setLogging(){
	    try {
	        LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
	    } catch (SecurityException | IOException e1) {
	        e1.printStackTrace();
	    } 	
	}

}