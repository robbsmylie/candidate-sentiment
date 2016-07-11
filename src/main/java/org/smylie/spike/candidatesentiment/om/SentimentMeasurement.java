package org.smylie.spike.candidatesentiment.om;

import java.util.Date;

public class SentimentMeasurement {

	private int sentimentMeasurementKey;
	private int siteKey;
	private int candidateKey;
	private String sentiment = null;
	private String sentimentType = null;
	private String url = null;
	private Date measurementDate = null;

	public int getSentimentMeasurementKey() {
		return sentimentMeasurementKey;
	}
	public void setSentimentMeasurementKey(int sentimentMeasurementKey) {
		this.sentimentMeasurementKey = sentimentMeasurementKey;
	}
	public int getSiteKey() {
		return siteKey;
	}
	public void setSiteKey(int siteKey) {
		this.siteKey = siteKey;
	}
	public int getCandidateKey() {
		return candidateKey;
	}
	public void setCandidateKey(int candidateKey) {
		this.candidateKey = candidateKey;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getMeasurementDate() {
		return measurementDate;
	}
	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}
	public String getSentimentType() {
		return sentimentType;
	}
	public void setSentimentType(String sentimentType) {
		this.sentimentType = sentimentType;
	}
}