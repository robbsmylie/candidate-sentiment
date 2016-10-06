package org.smylie.spike.candidatesentiment.watson;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jayway.jsonpath.JsonPath;

/**
 * Sentiment Analysis doc: https://www.alchemyapi.com/api/sentiment/proc.html
 *
 */

@Component
public class WatsonWrapper 
{
	public static String baseUrl = "http://gateway-a.watsonplatform.net/";
	public static String apiSentimentAnalysisBaseUrl = "calls/url/URLGetTargetedSentiment";
	public static String apiEntitiesBaseUrl = "calls/url/URLGetRankedNamedEntities";
	public static String apiTaxonomyBaseUrl = "calls/url/URLGetRankedTaxonomy";
	public static String apiConceptsBaseUrl = "calls/url/URLGetRankedConcepts";
	public static String apiKeywordsBaseUrl = "calls/url/URLGetRankedKeywords";
	
	@Value("${WATSON_API_KEY}")
	private String apiKey;
	public void setApiKey(String key) {
		apiKey = key;
	}
	
	public WatsonResponse callSentimentAnalysisService(String targetUrl, String keyPhrase) {

		String resultJson = null;

		// setup the url
		String urlCall = baseUrl+apiSentimentAnalysisBaseUrl;
		
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlCall)
		        .queryParam("outputMode", "json")
		        .queryParam("url", targetUrl)
		        .queryParam("targets", keyPhrase)
		        .queryParam("apikey", apiKey);
		
		resultJson = executeCall(urlBuilder.build().encode().toUri());
		
		System.out.println("sentiment result: "+resultJson);
		
		WatsonResponse response = new WatsonResponse();

		String status = JsonPath.read(resultJson,"$.status");				
		response.put("status", status);
		if(status.equals("ERROR")) {
			// do something
			System.out.println("Error when calling url: "+targetUrl);
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

	
	public String callEntitiesService(String targetUrl) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiEntitiesBaseUrl;

		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlCall)
		        .queryParam("outputMode", "json")
		        .queryParam("url", targetUrl)
		        .queryParam("apikey", apiKey);

		result = executeCall(urlBuilder.build().encode().toUri());
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callTaxonomyService(String targetUrl) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiTaxonomyBaseUrl;

		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlCall)
		        .queryParam("outputMode", "json")
		        .queryParam("url", targetUrl)
		        .queryParam("apikey", apiKey);

		result = executeCall(urlBuilder.build().encode().toUri());
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callConceptsService(String targetUrl) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiConceptsBaseUrl;

		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlCall)
		        .queryParam("outputMode", "json")
		        .queryParam("knowledgeGraph", "1")
		        .queryParam("url", targetUrl)
		        .queryParam("apikey", apiKey);

		result = executeCall(urlBuilder.build().encode().toUri());
		
		System.out.println("entities result: "+result);
		return result;
	}

	public String callKeywordsService(String targetUrl) {

		String result = null;

		// setup the url
		String urlCall = baseUrl+apiKeywordsBaseUrl;

		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlCall)
		        .queryParam("outputMode", "json")
		        .queryParam("sentiment", "1")
		        .queryParam("url", targetUrl)
		        .queryParam("apikey", apiKey);

		result = executeCall(urlBuilder.build().encode().toUri());
		
		System.out.println("entities result: "+result);
		return result;
	}
	
	public String executeCall(URI uri) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(
		        uri, 
		        String.class);
		
		return response.getBody();
	}
}
