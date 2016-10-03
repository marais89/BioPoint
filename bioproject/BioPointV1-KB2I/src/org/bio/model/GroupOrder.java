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
@Table(name = "groupOrder", catalog = "BiopointKb2i")
public class GroupOrder implements java.io.Serializable {
	
	private int idGroupOrder;
	private Rapport rapport;
	private String designation;
	private Boolean groupOrder;
	private Boolean etat;
	
	
	public GroupOrder ()
	{}
	public GroupOrder (int idGroupOrder)
	{this.idGroupOrder=idGroupOrder;}
	public GroupOrder (int idGroupOrder, Rapport rapport, String designation, Boolean groupOrder, Boolean etat)
	{this.idGroupOrder= idGroupOrder;this.rapport= rapport;this.designation=designation;this.groupOrder=groupOrder; this.etat=etat;}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idgroupOrder", unique = true, nullable = false)
	public int getIdGroupOrder() {
		return idGroupOrder;
	}
	public void setIdGroupOrder(int idGroupOrder) {
		this.idGroupOrder = idGroupOrder;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idrapport")
	public Rapport getRapport() {
		return rapport;
	}		
	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}
	
	
	@Column(name = "designation", length = 25, nullable = false)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
		
	
	@Column(name = "groupOrder", length = 25, nullable = false)
	public Boolean getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(Boolean groupOrder) {
		this.groupOrder = groupOrder;
	}
		
	@Column(name = "etat", length = 25, nullable = false)
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	

}
