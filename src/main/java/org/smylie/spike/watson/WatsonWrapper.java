package org.smylie.spike.watson;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Sentiment Analysis doc: https://www.alchemyapi.com/api/sentiment/proc.html
 *
 */
public class WatsonWrapper 
{
	public static String baseUrl = "http://gateway-a.watsonplatform.net/";
	public static String apiSentimentAnalysisUrl = "calls/url/URLGetTargetedSentiment?outputMode=json&apikey=$APIKEY&url=$URL&targets=$TARGET";
	public static String apiKey = "6e2c73e094df57ebe655eab852cc3feb475e9d2b";

	public String callSentimentAnalysis(String url) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiSentimentAnalysisUrl;
		urlCall = replaceArgument(urlCall,"$APIKEY",apiKey);
		urlCall = replaceArgument(urlCall,"$URL",url);
		urlCall = replaceArgument(urlCall,"$TARGET","Cruz");

		// call the service
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
		
		System.out.println("result: "+result);
		return result;
	}

	public String replaceArgument(String url, String arg, String val) {

		return url.replace(arg,val);
	}
}
