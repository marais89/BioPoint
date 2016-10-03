package org.bio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modeleRapport", catalog = "BiopointKb2i")
public class ModeleRapport implements java.io.Serializable{
	
	private int idModeleRapport;
	private String designationModele;
	
	public ModeleRapport()
	{}
	public ModeleRapport(int idModeleRapport)
	{this.idModeleRapport=idModeleRapport;}
	public ModeleRapport(int idModeleRapport,String designationModele)
	{this.idModeleRapport=idModeleRapport;this.designationModele=designationModele;}
	
	public boolean equals(ModeleRapport mr)
	{
		return(this.designationModele.equals(mr.designationModele)&& this.idModeleRapport==mr.idModeleRapport);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idModeleRapport", unique = true, nullable = false)
	public int getIdmot() {
		return this.idModeleRapport;
	}

	public void setIdmot(int idModeleRapport) {
		this.idModeleRapport = idModeleRapport;
	}

	@Column(name = "Designation", length = 20)
	public String getDesignationModele() {
		return this.designationModele;
	}

	public void setDesignationModele(String designation) {
		this.designationModele = designation;
	}
/*	@Override
	public String toString() {
		return "ModeleRapport [idModeleRapport=" + idModeleRapport + ", designationModele=" + designationModele;
	}*/
}
