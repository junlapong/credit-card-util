package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BinLookup {
	
	private static final Logger logger = LoggerFactory.getLogger(BinLookup.class);

	private static String userId;
	private static String apiKey;

	public BinLookup(String userId, String apiKey) {
		this.userId = userId;
		this.apiKey = apiKey;
	}

	/**
	 * 
	 * <a href="https://www.neutrinoapi.com/api/bin-lookup/">bin-lookup</a>
	 * 
	 * @param bin
	 */
	public void check(String bin, String customerIp) {
		
		try {
			
			HttpPost httpPost = new HttpPost("https://neutrinoapi.com/bin-lookup");
			List<NameValuePair> postData = new ArrayList<>();
			// USER_ID, API_KEY
			postData.add(new BasicNameValuePair("user-id", this.userId));
			postData.add(new BasicNameValuePair("api-key", this.apiKey));
			postData.add(new BasicNameValuePair("bin-number", bin));
//			postData.add(new BasicNameValuePair("customer-ip", customerIp)); // optional
			httpPost.setEntity(new UrlEncodedFormEntity(postData));

			HttpResponse response = HttpClients.createDefault().execute(httpPost);
			String jsonStr = EntityUtils.toString(response.getEntity());
			
			ObjectMapper mapper = new ObjectMapper();
			Object json = mapper.readValue(jsonStr, Object.class);	
			logger.debug("json: \n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

		} catch (IOException | ParseException | JSONException ex) {
			ex.printStackTrace();
			logger.error("ERROR: {}", ex.getMessage());
		}
	}
	
	/**
	 * 
	 * <a href="https://www.neutrinoapi.com/api/bin-lookup/">bin-lookup</a>
	 * 
	 * @param ipAddress
	 */
	public void checkIp(String ipAddress) {
		
		try {
			
			HttpPost httpPost = new HttpPost("https://neutrinoapi.com/ip-info");
			List<NameValuePair> postData = new ArrayList<>();
			// USER_ID, API_KEY
			postData.add(new BasicNameValuePair("user-id", this.userId));
			postData.add(new BasicNameValuePair("api-key", this.apiKey));
			postData.add(new BasicNameValuePair("ip", ipAddress));
			httpPost.setEntity(new UrlEncodedFormEntity(postData));

			HttpResponse response = HttpClients.createDefault().execute(httpPost);
			String jsonStr = EntityUtils.toString(response.getEntity());
			logger.debug("json: {}", jsonStr);
			
			JSONObject json = new JSONObject(jsonStr);
			logger.debug("Country : {} - {}", json.getString("country-code"), json.getString("country"));
			logger.debug("Region  : {}", json.getString("region"));
			logger.debug("City    : {}", json.getString("city"));
		} catch (IOException | ParseException | JSONException ex) {
			ex.printStackTrace();
			logger.error("ERROR: {}", ex.getMessage());
		}
	}
}
