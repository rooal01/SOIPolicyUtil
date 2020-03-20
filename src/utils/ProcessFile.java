package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessFile {
	
	public List<String[]> processFile(String FileName, int ParamCount){
		
		List<String[]> allPolicies = new ArrayList<String[]>();
        String csvFile = FileName;
        BufferedReader br = null;
        String line = "";
        int lineCount = 0;
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	lineCount++;
                // use comma as separator
                String[] policySetting = line.split(cvsSplitBy);
                if(policySetting.length < ParamCount) {

                System.out.println("Cound not process line number: "+lineCount+ " Expected number of parameters = "+ ParamCount + " found = " + policySetting.length);

            } else {
            	
            	allPolicies.add(policySetting);
            }
            }
        }

            catch (FileNotFoundException e) {
            System.out.print("File not found");
            System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return allPolicies;
		
		
	}

}
