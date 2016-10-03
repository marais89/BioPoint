package org.bio.model;

// Generated 19 mars 2014 17:02:06 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Identification generated by hbm2java
 */
@Entity
@Table(name = "identification")
public class Identification implements java.io.Serializable {

	private IdentificationId id;
	private Personnel personnel;
	private String valeurText;
	private String valeurBlob;

	public Identification() {
	}

	public Identification(IdentificationId id, Personnel personnel) {
		this.id = id;
		this.personnel = personnel;
	}

	public Identification(IdentificationId id, Personnel personnel,
			String valeurText, String valeurBlob) {
		this.id = id;
		this.personnel = personnel;
		this.valeurText = valeurText;
		this.valeurBlob = valeurBlob;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idper", column = @Column(name = "idper", nullable = false)),
			@AttributeOverride(name = "indexiden", column = @Column(name = "indexiden", nullable = false)),
			@AttributeOverride(name = "typeiden", column = @Column(name = "typeiden", nullable = false, length = 20)) })
	public IdentificationId getId() {
		return this.id;
	}

	public void setId(IdentificationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idper", nullable = false, insertable = false, updatable = false)
	public Personnel getPersonnel() {
		return this.personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	@Column(name = "valeurText", length = 20)
	public String getValeurText() {
		return this.valeurText;
	}

	public void setValeurText(String valeurText) {
		this.valeurText = valeurText;
	}

	
	@Column(name = "valeurBlob",length=100000)
	public String getValeurBlob() {
		return this.valeurBlob;
	}

	public void setValeurBlob(String valeurBlob) {
		this.valeurBlob = valeurBlob;
	}

}