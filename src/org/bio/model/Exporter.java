package org.bio.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "exporter")
public class Exporter implements Serializable{

	private Integer idexporter;
	private String name;
	private Date du;
	private Date au;
	private Affiliation affiliation;
	private Set<ExportOption> listoptions = new HashSet<ExportOption>();
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + ((au == null) ? 0 : au.hashCode());
		result = prime * result + ((du == null) ? 0 : du.hashCode());
		result = prime * result
				+ ((idexporter == null) ? 0 : idexporter.hashCode());
		result = prime * result
				+ ((listoptions == null) ? 0 : listoptions.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exporter other = (Exporter) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (au == null) {
			if (other.au != null)
				return false;
		} else if (!au.equals(other.au))
			return false;
		if (du == null) {
			if (other.du != null)
				return false;
		} else if (!du.equals(other.du))
			return false;
		if (idexporter == null) {
			if (other.idexporter != null)
				return false;
		} else if (!idexporter.equals(other.idexporter))
			return false;
		if (listoptions == null) {
			if (other.listoptions != null)
				return false;
		} else if (!listoptions.equals(other.listoptions))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exporter [idexporter=" + idexporter + ", name=" + name
				+ ", du=" + du + ", au=" + au + ", affiliation=" + affiliation
				+ ", listoptions=" + listoptions + "]";
	}
	public Exporter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exporter(Integer idexporter, String name, Date du, Date au,
			Affiliation affiliation, Set<ExportOption> listoptions) {
		super();
		this.idexporter = idexporter;
		this.name = name;
		this.du = du;
		this.au = au;
		this.affiliation = affiliation;
		this.listoptions = listoptions;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idexporter", unique = true, nullable = false)
	public Integer getIdexporter() {
		return idexporter;
	}
	public void setIdexporter(Integer idexporter) {
		this.idexporter = idexporter;
	}
	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "du", length = 10)
	public Date getDu() {
		return du;
	}
	public void setDu(Date du) {
		this.du = du;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "au", length = 10)
	public Date getAu() {
		return au;
	}
	public void setAu(Date au) {
		this.au = au;
	}
	@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idaff")
	public Affiliation getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "exporter")
	public Set<ExportOption> getListoptions() {
		return listoptions;
	}
	public void setListoptions(Set<ExportOption> listoptions) {
		this.listoptions = listoptions;
	}
}
