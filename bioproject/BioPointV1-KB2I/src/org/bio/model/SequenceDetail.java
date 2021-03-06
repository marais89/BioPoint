package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SequenceDetail generated by hbm2java
 */
@Entity
@Table(name = "sequence_detail")
public class SequenceDetail implements java.io.Serializable {

	private SequenceDetailId id;
	private Sequence idseq;
	private Date e1;
	private Date s1;
	private Date e2;
	private Date s2;
	private Date pause;
	private Integer jour;
	private Integer libJour;
	private Integer nbSeance;
	private Integer nuit;
	private Date nbHeures;
	private Set<Pointage> pointages = new HashSet<Pointage>(0);

	public SequenceDetail() {
	}

	public SequenceDetail(SequenceDetailId id, Sequence idseq) {
		this.id = id;
		this.idseq = idseq;
	}

	public SequenceDetail(SequenceDetailId id, Sequence idseq, Date e1, Date s1,
			Date e2, Date s2, Date pause, Integer libJour, Integer nbSeance,
			Integer nuit,Date nbHeures, Set<Pointage> pointages) {
		this.id = id;
		this.idseq = idseq;
		this.e1 = e1;
		this.s1 = s1;
		this.e2 = e2;
		this.s2 = s2;
		this.pause = pause;
		this.libJour = libJour;
		this.nbSeance = nbSeance;
		this.nuit = nuit;
		this.nbHeures=nbHeures;
		this.pointages = pointages;
	}
	
	public SequenceDetail(SequenceDetailId id, Sequence idseq, Integer libJour, Integer nbSeance,
			Integer nuit,Date nbHeures, Set<Pointage> pointages) {
		this.id = id;
		this.idseq = idseq;
		this.libJour = libJour;
		this.nbSeance = nbSeance;
		this.nuit = nuit;
		this.nbHeures=nbHeures;
		this.pointages = pointages;
	}
	
	public SequenceDetail(SequenceDetailId id, Sequence idseq, Date e1, Date s1,
			Date pause, Integer libJour, Integer nbSeance,
			Integer nuit,Date nbHeures, Set<Pointage> pointages) {
		this.id = id;
		this.idseq = idseq;
		this.e1 = e1;
		this.s1 = s1;
		this.pause = pause;
		this.libJour = libJour;
		this.nbSeance = nbSeance;
		this.nuit = nuit;
		this.nbHeures=nbHeures;
		this.pointages = pointages;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idseqd", column = @Column(name = "idseqd", nullable = false)),
			@AttributeOverride(name = "jour", column = @Column(name = "jour", nullable = false, length = 10)) })
	public SequenceDetailId getId() {
		return this.id;
	}

	public void setId(SequenceDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idseq")
	public Sequence getIdseq() {
		return this.idseq;
	}

	public void setIdseq(Sequence idseq) {
		this.idseq = idseq;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "e1", length = 8)
	public Date getE1() {
		return this.e1;
	}

	public void setE1(Date e1) {
		this.e1 = e1;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "s1", length = 8)
	public Date getS1() {
		return this.s1;
	}

	public void setS1(Date s1) {
		this.s1 = s1;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "e2", length = 8)
	public Date getE2() {
		return this.e2;
	}

	public void setE2(Date e2) {
		this.e2 = e2;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "s2"	)
	public Date getS2() {
		return this.s2;
	}

	public void setS2(Date s2) {
		this.s2 = s2;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "pause", length = 8)
	public Date getPause() {
		return this.pause;
	}

	public void setPause(Date pause) {
		this.pause = pause;
	}

	@Column(name = "libJour")
	public Integer getLibJour() {
		return this.libJour;
	}

	public void setLibJour(Integer libJour) {
		this.libJour = libJour;
	}

	@Column(name = "nbSeance")
	public Integer getNbSeance() {
		return this.nbSeance;
	}

	public void setNbSeance(Integer nbSeance) {
		this.nbSeance = nbSeance;
	}

	@Column(name = "nuit")
	public Integer getNuit() {
		return this.nuit;
	}

	public void setNuit(Integer nuit) {
		this.nuit = nuit;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name = "nbHeures", length = 8)
	public Date getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(Date nbHeures) {
		this.nbHeures = nbHeures;
	}

	@Override
	public String toString() {
		return "SequenceDetail [id=" + id + ", e1=" + e1 + ", s1=" + s1
				+ ", e2=" + e2 + ", s2=" + s2 + ", pause=" + pause + ", jour="
				+ jour + ", libJour=" + libJour + ", nbSeance=" + nbSeance+
				", nbHeure=" + nbHeures+ ", nuit=" + nuit ;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sequenceDetail")
	public Set<Pointage> getPointages() {
		return this.pointages;
	}

	public void setPointages(Set<Pointage> pointages) {
		this.pointages = pointages;
	}
	@Column(name = "jour",insertable=false,updatable=false)
	public Integer getJour() {
		return jour;
	}

	public void setJour(Integer jour) {
		this.jour = jour;
	}

}
