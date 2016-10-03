package org.bio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parametre")
public class Parametre implements Serializable{
	


	private String nomparam;
	private String valueparam;
	private String description;
private String label;


	
	public Parametre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Parametre(String nomparam, String valueparam, String description) {
		super();
		this.nomparam = nomparam;
		this.valueparam = valueparam;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Parametre [nomparam=" + nomparam + ", valueparam=" + valueparam
				+ ", description=" + description + ", label=" + label + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((nomparam == null) ? 0 : nomparam.hashCode());
		result = prime * result
				+ ((valueparam == null) ? 0 : valueparam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametre other = (Parametre) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (nomparam == null) {
			if (other.nomparam != null)
				return false;
		} else if (!nomparam.equals(other.nomparam))
			return false;
		if (valueparam == null) {
			if (other.valueparam != null)
				return false;
		} else if (!valueparam.equals(other.valueparam))
			return false;
		return true;
	}

	@Id
	@Column(name = "nomparam", unique = true, nullable = false)
	public String getNomparam() {
		return nomparam;
	}
	public void setNomparam(String nomparam) {
		this.nomparam = nomparam;
	}
	@Column(name="valueparam")
	public String getValueparam() {
		return valueparam;
	}
	public void setValueparam(String valueparam) {
		this.valueparam = valueparam;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
@Column(name="label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}





}
