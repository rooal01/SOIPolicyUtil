package configuration;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Commandline {

	
	
	public static Options cmdopts() throws ParseException {
	// create Options object
	Options options = new Options();


//	options.addOption("gp", "getPolicy", true, "Get Policy by Name")
//    .addOption("cp", "createPolicy", true, "Create Policy via input file")
//    .addOption("ga", "getAction", true, "Get Action by Name");
	
	Option createAction   = Option.builder()
	         .longOpt("createAction")
	         .argName("file name with action details" )
	         .hasArg()
	         .desc("Create a new Action from file" )
	         .build();
	
	Option createPolicy   = Option.builder()
	         .longOpt("createPolicy")
	         .argName("file anme with policy details" )
	         .hasArg()
	         .desc("Create a new Policy from a file" )
	         .build();
	
	Option getPolicy   = Option.builder()
	         .longOpt("getPolicy")
	         .argName("Name of Policy" )
	         .hasArg()
	         .desc("Get policy XML" )
	         .build();
	
	Option getAction   = Option.builder()
	         .longOpt("getAction")
	         .argName("Name of Action")
	         .hasArg()
	         .desc("Get Action Policy XML" )
	         .build();
	
	options.addOption(createAction).addOption(createPolicy).addOption(getPolicy).addOption(getAction);
	
	return options;

	}
}
