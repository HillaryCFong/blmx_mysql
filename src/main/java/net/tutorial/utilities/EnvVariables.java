package net.tutorial.utilities;


import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class EnvVariables {
	
	boolean hasVcap = false;
	JSONObject vcap = null;
	
	public EnvVariables() {
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
				
		if (VCAP_SERVICES != null) {
			this.hasVcap = true;
			JSONParser parser = new JSONParser();
			Object obj;
			
			try {
				obj = parser.parse(VCAP_SERVICES);
				vcap =  (JSONObject) obj;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public Map<String, String> getCredentials(String serviceName) {
		
		Map<String, String> creds = new HashMap<String, String>();
		
		if (this.hasVcap) {
			JSONArray serviceConfig = (JSONArray) vcap.get(serviceName);
	        JSONObject serviceInstance = (JSONObject) serviceConfig.get(0);
	        JSONObject serviceCreds = (JSONObject) serviceInstance.get("credentials");
			
		//	creds.put("jdbcUrl", serviceCreds.get("jdbcUrl").toString());
		creds.put("jdbcUrl", "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_1a3e20df4785a73?user=bfc3e0aefffbde&password=23266933"); 
			creds.put("username", serviceCreds.get("username").toString());
			creds.put("password",  serviceCreds.get("password").toString());
		} else {
			creds.put("jdbcUrl", "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_1a3e20df4785a73?user=bfc3e0aefffbde&password=23266933"); // Put username here if you are testing in local
			creds.put("username", "b1e52a1f-3ac8-4720-8b48-0fff059d339b"); // Put username here if you are testing in local
			creds.put("password",  "6xFBkXxxgAPC"); // Put password here if you are testing in local

		}
		
		return creds;
	}

}
