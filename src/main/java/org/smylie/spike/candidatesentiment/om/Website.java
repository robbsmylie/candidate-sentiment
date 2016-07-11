package org.smylie.spike.candidatesentiment.om;

public class Website {

	private int websiteKey;
	private String name = null;
	private String url = null;

	public int getWebsiteKey() {
		return websiteKey;
	}
	public void setWebsiteKey(int websiteKey) {
		this.websiteKey = websiteKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	

}