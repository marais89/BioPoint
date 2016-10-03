package org.bio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sbi_operateur_affiliation")
public class SbiOperateurAffiliation implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idsbi;
	private Integer idaff;
	private Integer idop;
	
	
	public SbiOperateurAffiliation() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idaff == null) ? 0 : idaff.hashCode());
		result = prime * result + ((idop == null) ? 0 : idop.hashCode());
		result = prime * result + ((idsbi == null) ? 0 : idsbi.hashCode());
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
		SbiOperateurAffiliation other = (SbiOperateurAffiliation) obj;
		if (idaff == null) {
			if (other.idaff != null)
				return false;
		} else if (!idaff.equals(other.idaff))
			return false;
		if (idop == null) {
			if (other.idop != null)
				return false;
		} else if (!idop.equals(other.idop))
			return false;
		if (idsbi == null) {
			if (other.idsbi != null)
				return false;
		} else if (!idsbi.equals(other.idsbi))
			return false;
		return true;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idsbi", unique = true, nullable = false)
	public Integer getIdsbi() {
		return idsbi;
	}
	public void setIdsbi(Integer idsbi) {
		this.idsbi = idsbi;
	}
	public Integer getIdaff() {
		return idaff;
	}
	public void setIdaff(Integer idaff) {
		this.idaff = idaff;
	}
	public Integer getIdop() {
		return idop;
	}
	public void setIdop(Integer idop) {
		this.idop = idop;
	}
}
