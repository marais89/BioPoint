package org.bio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class LostMvtId implements Serializable{

	private Date jour;
	private Date heure;
	private Integer bckId;
	
	public LostMvtId() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bckId == null) ? 0 : bckId.hashCode());
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + ((jour == null) ? 0 : jour.hashCode());
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
		LostMvtId other = (LostMvtId) obj;
		if (bckId == null) {
			if (other.bckId != null)
				return false;
		} else if (!bckId.equals(other.bckId))
			return false;
		if (heure == null) {
			if (other.heure != null)
				return false;
		} else if (!heure.equals(other.heure))
			return false;
		if (jour == null) {
			if (other.jour != null)
				return false;
		} else if (!jour.equals(other.jour))
			return false;
		return true;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "jour", length = 10)
	public Date getJour() {
		return jour;
	}
	public void setJour(Date jour) {
		this.jour = jour;
	}
	@Temporal(TemporalType.TIME)
	@Column(name = "heure", length = 8)
	public Date getHeure() {
		return heure;
	}
	public void setHeure(Date heure) {
		this.heure = heure;
	}
	@Column(name = "bckId")
	public Integer getBckId() {
		return bckId;
	}
	public void setBckId(Integer bckId) {
		this.bckId = bckId;
	}
}
