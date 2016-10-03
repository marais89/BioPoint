package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ferie generated by hbm2java
 */
@Entity
@Table(name = "ferie")
public class Ferie implements java.io.Serializable {

	private int idfer;
	private String designation;
	private Date dateDebut;
	private Date dateFin;
	private String type;
	private Boolean paye;
	private Set<GlobalHistorique> globalHistoriques = new HashSet<GlobalHistorique>(
			0);

	public Ferie() {
	}

	public Ferie(int idfer) {
		this.idfer = idfer;
	}

	public Ferie(int idfer, String designation, Date dateDebut, Date dateFin,
			String type, Boolean paye, Set<GlobalHistorique> globalHistoriques) {
		this.idfer = idfer;
		this.designation = designation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.paye = paye;
		this.globalHistoriques = globalHistoriques;
	}

	@Id
	@Column(name = "idfer", unique = true, nullable = false)
	public int getIdfer() {
		return this.idfer;
	}

	public void setIdfer(int idfer) {
		this.idfer = idfer;
	}

	@Column(name = "designation", length = 20)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateDebut", length = 10)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateFin", length = 10)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "paye")
	public Boolean getPaye() {
		return this.paye;
	}

	public void setPaye(Boolean paye) {
		this.paye = paye;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ferie")
	public Set<GlobalHistorique> getGlobalHistoriques() {
		return this.globalHistoriques;
	}

	public void setGlobalHistoriques(Set<GlobalHistorique> globalHistoriques) {
		this.globalHistoriques = globalHistoriques;
	}

}
