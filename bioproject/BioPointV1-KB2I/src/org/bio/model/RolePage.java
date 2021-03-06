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

/**
 * RolePage generated by hbm2java
 */
@Entity
@Table(name = "role_page", catalog = "BiopointKb2i")
public class RolePage implements java.io.Serializable {

	private RolePageId id;
	private Page page;
	private Role role;
	private Byte w;
	private Boolean r;

	public RolePage() {
	}

	public RolePage(RolePageId id, Page page, Role role) {
		this.id = id;
		this.page = page;
		this.role = role;
	}

	public RolePage(RolePageId id, Page page, Role role, Byte w, Boolean r) {
		this.id = id;
		this.page = page;
		this.role = role;
		this.w = w;
		this.r = r;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idrol", column = @Column(name = "idrol", nullable = false)),
			@AttributeOverride(name = "idpag", column = @Column(name = "idpag", nullable = false)) })
	public RolePageId getId() {
		return this.id;
	}

	public void setId(RolePageId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpag", nullable = false, insertable = false, updatable = false)
	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idrol", nullable = false, insertable = false, updatable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "w")
	public Byte getW() {
		return this.w;
	}

	public void setW(Byte w) {
		this.w = w;
	}

	@Column(name = "r")
	public Boolean getR() {
		return this.r;
	}

	public void setR(Boolean r) {
		this.r = r;
	}

}
