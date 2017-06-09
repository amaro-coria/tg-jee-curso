package net.tecgurus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
	@NamedQuery(name="Role.findAllActive", query="SELECT r FROM Role r WHERE r.id.nombRol like 'ALGUN_STRING'")
})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePK id;

	private byte enabled;

	public Role() {
	}

	public RolePK getId() {
		return this.id;
	}

	public void setId(RolePK id) {
		this.id = id;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

}