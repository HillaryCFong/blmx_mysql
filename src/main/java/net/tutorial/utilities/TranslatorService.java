package net.tutorial.utilities;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

public class TranslatorService {

	LanguageTranslator service;
	
	public TranslatorService() {
		EnvVariables envVar = new EnvVariables();
		Map<String, String> creds = envVar.getCredentials("language_translator");
		
		service = new LanguageTranslator();
		service.setUsernameAndPassword("05d75cae-a529-4f1e-b04a-6dd2bef7c428", "S5XJBP2SMnP2");
	}
	
	public String getTranslation(String text, String modelId) {
		TranslationResult result = service.translate(text, modelId + "-conversational").execute();
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONObject jsonTranslationResult = (JSONObject) parser.parse(result.toString() );
			JSONArray jsonTranslations = (JSONArray) jsonTranslationResult.get("translations");
			JSONObject jsonTranslation = (JSONObject) jsonTranslations.get(0);
			
			return jsonTranslation.get("translation").toString();
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

}
