package org.bio.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "regles", catalog = "BiopointKb2i")
public class Regles implements java.io.Serializable {
	
	private int idregles;
	private Rapport rapport;
	private String regle;
	
	public Regles ()
	{}
	public Regles (int idregles)
	{this.idregles=idregles;}
	public Regles (int idregles ,Rapport rapport ,String regle)
	{this.idregles=idregles;this.rapport=rapport; this.regle=regle;}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idregles", unique = true, nullable = false)
	public int getIdregles() {
		return this.idregles;
	}

	public void setIdregles(int idregles) {
		this.idregles = idregles;
	}
///////////////idRapport
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idrapport")
	public Rapport getRapport() {
		return rapport;
	}
	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}

	
	@Column(name = "regle", length = 45, nullable = false)
	public String getchamps() {
		return this.regle;
	}
	
	public void setchamps(String regle) {
		this.regle = regle;
	}
	
}
