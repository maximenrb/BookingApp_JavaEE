package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Picture {

	@Column(nullable = false)
	private String url;
	
	public Picture() {}
	
	/**
	 * @param accommodation
	 * @param url
	 */
	public Picture(String url) {
		super();
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString() {
		return "Filename: " + url;
	}
	
}
