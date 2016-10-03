package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Motif generated by hbm2java
 */
@Entity
@Table(name = "motif")
public class Motif implements java.io.Serializable {

	private int idmot;
	private String designation;
	private Set<Conge> conges = new HashSet<Conge>(0);

	public Motif() {
	}

	public Motif(int idmot) {
		this.idmot = idmot;
	}

	public Motif(int idmot, String designation, Set<Conge> conges) {
		this.idmot = idmot;
		this.designation = designation;
		this.conges = conges;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idmot", unique = true, nullable = false)
	public int getIdmot() {
		return this.idmot;
	}

	public void setIdmot(int idmot) {
		this.idmot = idmot;
	}

	@Column(name = "designation", length = 20)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motif")
	public Set<Conge> getConges() {
		return this.conges;
	}

	public void setConges(Set<Conge> conges) {
		this.conges = conges;
	}

}
