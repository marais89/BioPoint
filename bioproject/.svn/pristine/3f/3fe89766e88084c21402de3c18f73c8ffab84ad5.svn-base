package org.bio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Embeddable
public class BatchId  implements java.io.Serializable{
	private Integer idper;
	private Date jour;
	
	public BatchId(Integer idper, Date jour) {
		this.idper = idper;
		this.jour = jour;
	}
	
	public BatchId() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idper == null) ? 0 : idper.hashCode());
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
		BatchId other = (BatchId) obj;
		if (idper == null) {
			if (other.idper != null)
				return false;
		} else if (!idper.equals(other.idper))
			return false;
		if (jour == null) {
			if (other.jour != null)
				return false;
		} else if (!jour.equals(other.jour))
			return false;
		return true;
	}
	@Column(name = "idper", nullable = false, length = 10)
	public Integer getIdper() {
		return idper;
	}

	public void setIdper(Integer idper) {
		this.idper = idper;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "jour", nullable = false, length = 10)
	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

}
