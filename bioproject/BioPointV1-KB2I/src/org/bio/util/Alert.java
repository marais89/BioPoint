package org.bio.util;

import java.io.Serializable;

public class Alert implements Serializable{
	
	private String nbalert;
	private String description;
	private String icon;
	private String url;
	private String action;
	public Alert(String nbalert, String description,String icon) {
		super();
		this.nbalert = nbalert;
		this.description = description;
		this.setIcon(icon);
	}
	
	public Alert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNbalert() {
		return nbalert;
	}
	public void setNbalert(String nbalert) {
		this.nbalert = nbalert;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

}
