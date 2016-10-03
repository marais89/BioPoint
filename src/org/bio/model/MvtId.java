package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * MvtId generated by hbm2java
 */
@Embeddable
public class MvtId implements java.io.Serializable {

	@Override
	public String toString() {
		return "MvtId [idper=" + idper + ", idter=" + idter + "]";
	}

	private int idper;
	private int idter;
private int mvtid;

	public MvtId() {
	}

	public MvtId(int idper, int idter) {
		this.idper = idper;
		this.idter = idter;
	}

	@Column(name = "idper", nullable = false)
	public int getIdper() {
		return this.idper;
	}

	public void setIdper(int idper) {
		this.idper = idper;
	}

	@Column(name = "idter", nullable = false)
	public int getIdter() {
		return this.idter;
	}

	public void setIdter(int idter) {
		this.idter = idter;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MvtId))
			return false;
		MvtId castOther = (MvtId) other;

		return (this.getIdper() == castOther.getIdper())
				&& (this.getIdter() == castOther.getIdter());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdper();
		result = 37 * result + this.getIdter();
		return result;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idmvt", nullable = false)
	public int getMvtid() {
		return mvtid;
	}

	public void setMvtid(int mvtid) {
		this.mvtid = mvtid;
	}

}
