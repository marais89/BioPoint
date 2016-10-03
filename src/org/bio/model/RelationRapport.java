package org.bio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relationRapport", catalog = "BiopointKb2i")
public class RelationRapport implements java.io.Serializable{
	
	private int idrelation;
	private Rapport rapport;
	private int idPersAff;
	private String type;
	private Boolean indiceSelection;
	
	
	public RelationRapport ()
	{}
	public RelationRapport (int idrelation)
	{this.idrelation=idrelation;}
	public RelationRapport (int idrelation, int idPersAff, String type, Rapport rapport, Boolean indiceSelection)
	{this.idrelation= idrelation;this.idPersAff= idPersAff;this.type=type;this.rapport=rapport;this.indiceSelection=indiceSelection;}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idrelation", unique = true, nullable = false)
	public int getIdrelation() {
		return idrelation;
	}
	public void setIdrelation(int idrelation) {
		this.idrelation = idrelation;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idrapport")
	public Rapport getRapport() {
		return rapport;
	}
	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}
	
	@Column(name = "idPersAff", length = 25, nullable = false)
	public int getIdPersAff() {
		return idPersAff;
	}
	public void setIdPersAff(int idPersAff) {
		this.idPersAff = idPersAff;
	}
		
	
	@Column(name = "type", length = 25, nullable = false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "indiceSelection", nullable = true)
	public Boolean getIndiceSelection() {
		return indiceSelection;
	}
	public void setIndiceSelection(Boolean indiceSelection) {
		this.indiceSelection = indiceSelection;
	}
	

}
