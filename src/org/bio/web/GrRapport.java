package org.bio.web;

public class GrRapport {
	
	private String image;
	private String designation;
	
	public GrRapport(String im,String desig)
	{this.image=im;
	this.designation= desig;}

	
	/// GETTERS AND SETTERS 
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

}
