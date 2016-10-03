package org.bio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "batch")
public class Batch implements Serializable{

//	private BatchId id;
	@Override
	public String toString() {
		return "Batch [id=" + id + ", etat=" + etat + ", personnel="
				+ personnel + "]";
	}
	private int id;
	private Integer idper;
	private Date jour;
	private Integer etat;
	private Personnel personnel;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idbatch", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Batch other = (Batch) obj;
//		if (etat == null) {
//			if (other.etat != null)
//				return false;
//		} else if (!etat.equals(other.etat))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	@EmbeddedId
//	@AttributeOverrides({
//			@AttributeOverride(name = "jour", column = @Column(name = "jour", nullable = false, length = 10)),
//			@AttributeOverride(name = "idper", column = @Column(name = "idper", nullable = false)) })
	
//	public BatchId getId() {
//		return id;
//	}
	
	@Column(name = "idper", length = 25)
	public Integer getIdper() {
		return idper;
	}	
	public void setIdper(Integer idper) {
		this.idper = idper;
	}
	
	@Column(name = "jour", length = 25)
	public Date getJour() {
		return jour;
	}
	public void setJour(Date jour) {
		this.jour = jour;
	}
	
	@Column(name = "etat", length = 25)
	public Integer getEtat() {
		return etat;
	}
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	public Batch(int id, Integer etat) {
		this.id = id;
		this.etat = etat;
	}
	public Batch() {
		// TODO Auto-generated constructor stub
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idper", nullable = false, insertable = false, updatable = false)
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
	
}
