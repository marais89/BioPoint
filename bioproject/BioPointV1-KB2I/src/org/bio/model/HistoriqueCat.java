package org.bio.model;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "historique_cat")
public class HistoriqueCat implements java.io.Serializable {

	private int idhis;
	private Affiliation affiliation;
	private Personnel personnel;
	private Categorie categorie;
	private Date du;
	private Date au;
private Sequence sequence;
private Integer clejour;
private Sequence currentSeqeunce;
	public HistoriqueCat() {
	}

	public HistoriqueCat(int idhis, Affiliation affiliation, Personnel personnel,
			Categorie categorie) {
		this.idhis = idhis;
		this.affiliation = affiliation;
		this.personnel = personnel;
		this.categorie = categorie;
	}

	public HistoriqueCat(int idhis, Affiliation affiliation, Personnel personnel,
			Categorie categorie, Date du, Date au) {
		this.idhis = idhis;
		this.affiliation = affiliation;
		this.personnel = personnel;
		this.categorie = categorie;
		this.du = du;
		this.au = au;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idhistorique_cat", unique = true, nullable = false)
	public int getIdhis() {
		return this.idhis;
	}

	public void setIdhis(int idhis) {
		this.idhis = idhis;
	}

	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idper", nullable = false)
	public Personnel getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	//**
	@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcat", nullable = true)
	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Du", length = 10)
	public Date getDu() {
		return this.du;
	}

	public void setDu(Date du) {
		this.du = du;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Au", length = 10)
	public Date getAu() {
		return this.au;
	}

	public void setAu(Date au) {
		this.au = au;
	}
	

	@Override
	public String toString() {
		String s="";
	if(categorie!=null)
		return categorie.getDesigCat();
	else return sequence.getDesigSeq();
	}

	
	@Column(name = "cle_jour")
	public Integer getClejour() {
		return clejour;
	}

	public void setClejour(Integer clejour) {
		this.clejour = clejour;
	}
@Transient
	public Sequence getCurrentSeqeunce() {
		return currentSeqeunce;
	}

	public void setCurrentSeqeunce(Sequence currentSeqeunce) {
		this.currentSeqeunce = currentSeqeunce;
	}
	@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idseq", nullable = true)
	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	
	

}
