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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lostmvt")
public class LostMvt implements Serializable{

	private String moyen;
	private String mode;
	private Terminaux terminaux;
	private Date jourLogique;
	private LostMvtId id;
	private String bcketat;
	public LostMvt()
	{
	}
	public LostMvt(String moyen, String mode, Date jourLogique) {
		this.moyen = moyen;
		this.mode = mode;
		this.jourLogique = jourLogique;
		
	}
	@Column(name = "moyen",length = 30)
	public String getMoyen() {
		return moyen;
	}
	public void setMoyen(String moyen) {
		this.moyen = moyen;
	}
	@Column(name = "mode", length = 30)
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "jourLogique", length = 10)
	public Date getJourLogique() {
		return jourLogique;
	}
	public void setJourLogique(Date jourLogique) {
		this.jourLogique = jourLogique;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idter", nullable = false)
	public Terminaux getTerminaux() {
		return terminaux;
	}
	public void setTerminaux(Terminaux terminaux) {
		this.terminaux = terminaux;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "jour", column = @Column(name = "jour", nullable = false)),
			@AttributeOverride(name = "heure", column = @Column(name = "heure", nullable = false)),
			@AttributeOverride(name = "bckId", column = @Column(name = "bckId", nullable = false)) })
	public LostMvtId getId() {
		return id;
	}
	public void setId(LostMvtId id) {
		this.id = id;
	}
	@Column(name = "bcketat", length = 1)
	public String getBcketat() {
		return bcketat;
	}
	public void setBcketat(String bcketat) {
		this.bcketat = bcketat;
	}
	
}
