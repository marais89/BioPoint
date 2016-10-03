package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Trace generated by hbm2java
 */
@Entity
@Table(name = "trace")
public class Trace implements java.io.Serializable {

	private int idtr;
	private Operateur operateur;
	private String action;
	private Date dateHeure;
	private String objet;

	public Trace() {
	}

	public Trace(int idtr, Operateur operateur) {
		this.idtr = idtr;
		this.operateur = operateur;
	}

	public Trace(int idtr, Operateur operateur, String action, Date dateHeure) {
		this.idtr = idtr;
		this.operateur = operateur;
		this.action = action;
		this.dateHeure = dateHeure;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idtr", unique = true, nullable = false)
	public int getIdtr() {
		return this.idtr;
	}

	public void setIdtr(int idtr) {
		this.idtr = idtr;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idop", nullable = false)
	public Operateur getOperateur() {
		return this.operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	@Column(name = "action", length = 100)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateHeure", length = 19)
	public Date getDateHeure() {
		return this.dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
	@Column(name = "objet", length = 100)
	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

}