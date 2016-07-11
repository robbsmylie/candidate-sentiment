package org.smylie.spike.watson;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

/**
 * Sentiment Analysis doc: https://www.alchemyapi.com/api/sentiment/proc.html
 *
 */
public class WatsonWrapper 
{
	public static String baseUrl = "http://gateway-a.watsonplatform.net/";
	public static String apiSentimentAnalysisUrl = "calls/url/URLGetTargetedSentiment?outputMode=json&apikey=$APIKEY&url=$URL&targets=$TARGET";
	public static String apiEntitiesUrl = "calls/url/URLGetRankedNamedEntities?outputMode=json&apikey=$APIKEY&url=$URL";
	public static String apiTaxonomyUrl = "calls/url/URLGetRankedTaxonomy?outputMode=json&apikey=$APIKEY&url=$URL";
	public static String apiConceptsUrl = "calls/url/URLGetRankedConcepts?outputMode=json&apikey=$APIKEY&url=$URL&knowledgeGraph=1";
	public static String apiKeywordsUrl = "calls/url/URLGetRankedKeywords?outputMode=json&apikey=$APIKEY&url=$URL&sentiment=1";
	
	public static String apiKey = "6e2c73e094df57ebe655eab852cc3feb475e9d2b";

	public WatsonResponse callSentimentAnalysisService(String url, String keyPhrase) {

		String resultJson = null;

		// setup the url
		String urlCall = baseUrl+apiSentimentAnalysisUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);
		urlCall = replaceArgument(urlCall,"$TARGET",keyPhrase);

		resultJson = executeCall(urlCall);
		
		System.out.println("sentiment result: "+resultJson);
		
		WatsonResponse response = new WatsonResponse();

		String status = JsonPath.read(resultJson,"$.status");				
		response.put("status", status);
		if(status.equals("ERROR")) {
			// do something
			System.out.println("Error when calling url: "+url);
			System.out.println("Response was: "+resultJson);					
			return response;
		}
		
		// try to read sentiment
		// will not be available if type is Neutral, in that case store 0
		String sentimentType = JsonPath.read(resultJson,"$.results[0].sentiment.type");
		response.put("sentimenttype", sentimentType);
		if(sentimentType.equals("neutral")) {
			response.put("sentiment", "0");						
		}
		else {
			response.put("sentiment", JsonPath.read(resultJson,"$.results[0].sentiment.score"));			
		}
				
		
		return response;
	}

	public String callEntitiesService(String url) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiEntitiesUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);

		result = executeCall(urlCall);
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callTaxonomyService(String url) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiTaxonomyUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);

		result = executeCall(urlCall);
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callConceptsService(String url) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiConceptsUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);

		result = executeCall(urlCall);
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callKeywordsService(String url) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiKeywordsUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);

		result = executeCall(urlCall);
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String executeCall(String urlCall) {

		// call the service
		String result = null;
		CloseableHttpResponse response1 = null;
		try {
			System.out.println("calling url"+urlCall);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(urlCall);
			response1 = httpclient.execute(httpGet);
			result = EntityUtils.toString(response1.getEntity());
		}
		catch(ClientProtocolException e) {
			//TODO - something
		}
		catch(IOException e) {
			//TODO - something			
		}
		finally {
			try {
				response1.close();
			}
			catch(IOException e) {
				
			}
		}

		return result;
	}
	
	public String replaceArgument(String url, String arg, String val) {

		return url.replace(arg,val);
	}
}
