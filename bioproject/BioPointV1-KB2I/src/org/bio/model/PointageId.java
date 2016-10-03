package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PointageId generated by hbm2java
 */
@Embeddable
public class PointageId implements java.io.Serializable {

	private Date jour;
	private int idper;

	public PointageId() {
	}

	public PointageId(Date jour, int idper) {
		this.jour = jour;
		this.idper = idper;
	}

	@Column(name = "jour", nullable = false, length = 10)
	public Date getJour() {
		return this.jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	@Column(name = "idper", nullable = false)
	public int getIdper() {
		return this.idper;
	}

	public void setIdper(int idper) {
		this.idper = idper;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PointageId))
			return false;
		PointageId castOther = (PointageId) other;

		return ((this.getJour() == castOther.getJour()) || (this.getJour() != null
				&& castOther.getJour() != null && this.getJour().equals(
				castOther.getJour())))
				&& (this.getIdper() == castOther.getIdper());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getJour() == null ? 0 : this.getJour().hashCode());
		result = 37 * result + this.getIdper();
		return result;
	}

}
