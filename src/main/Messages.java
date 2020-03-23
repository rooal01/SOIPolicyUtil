package main;

public class Messages {
	
	static String InvalidParam;
	static String FileNotFound;
	static String NoPolicy;
	
	
	Messages(){
		
		//Could use Java commons CLI stuff for this
		InvalidParam = "Invalid Parameters Passed to command line.\n"
				+ "Valid parameters are as follows:\n"
				+ "[getAction|getPolicy|createAction|createPolicy] [file name for create options|name of policy for get option.]\n"
				+ "\nExamples:\n\n"
				+ "Get the XML for a SOI Action called myaction:\n\n  getAction myaction \n\n"
				+ "Get the XML for a SOI Escalation Policy with the name mypolicy:\n\n  getPolicy mypolicy \n\n"
				+ "Create action policy:\n\n  CreateAction action_policy_file_name \n\n"
				+ "Create escalation policy:\n\n  CreatePolicy escalation_policy_file_name \n\n"
				+ "";
		
		
		FileNotFound = "File cannot be read: ";
		
		NoPolicy="No policy found with name: ";
	}

}
