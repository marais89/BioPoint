package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Document generated by hbm2java
 */
@Entity
@Table(name = "document")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Document implements java.io.Serializable {

	private int iddoc;
	private Personnel personnel;
	private String designation;
	private String description;
	private byte[] source;
	private String typedoc;

	public Document() {
	}

	public Document(int iddoc, Personnel personnel) {
		this.iddoc = iddoc;
		this.personnel = personnel;
	}

	public Document(int iddoc, Personnel personnel, String designation,
			String description, byte[] source) {
		this.iddoc = iddoc;
		this.personnel = personnel;
		this.designation = designation;
		this.description = description;
		this.source = source;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "iddoc", unique = true, nullable = false)
	public int getIddoc() {
		return this.iddoc;
	}

	public void setIddoc(int iddoc) {
		this.iddoc = iddoc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idper", nullable = false)
	public Personnel getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	@Column(name = "designation", length = 100)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "description", length = 20)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    @Lob
	@Column(name = "source",columnDefinition = "LONGBLOB")
	public byte[] getSource() {
		return this.source;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}
	@Column(name = "typedoc", length = 20)
	public String getTypedoc() {
		return typedoc;
	}

	public void setTypedoc(String typedoc) {
		this.typedoc = typedoc;
	}

}
