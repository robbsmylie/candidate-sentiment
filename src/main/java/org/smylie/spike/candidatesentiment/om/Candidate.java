package org.smylie.spike.candidatesentiment.om;

public class Candidate {

	private int candidateKey;
	private String firstName = null;
	private String lastName = null;
	private String searchTerm = null;
	
	public int getCandidateKey() {
		return candidateKey;
	}

	public void setCandidateKey(int candidateKey) {
		this.candidateKey = candidateKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}