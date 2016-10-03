package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IdentificationId generated by hbm2java
 */
@Embeddable
public class IdentificationId implements java.io.Serializable {

	private int idper;
	private int index;
	private String type;

	public IdentificationId() {
	}

	public IdentificationId(int idper, int index, String type) {
		this.idper = idper;
		this.index = index;
		this.type = type;
	}

	@Column(name = "idper", nullable = false)
	public int getIdper() {
		return this.idper;
	}

	public void setIdper(int idper) {
		this.idper = idper;
	}

	@Column(name = "indexiden", nullable = false)
	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Column(name = "typeiden", nullable = false, length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IdentificationId))
			return false;
		IdentificationId castOther = (IdentificationId) other;

		return (this.getIdper() == castOther.getIdper())
				&& (this.getIndex() == castOther.getIndex())
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdper();
		result = 37 * result + this.getIndex();
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		return result;
	}

}
