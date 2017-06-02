package net.tecgurus.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the roles database table.
 * 
 */
@Embeddable
public class RolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_rol")
	private int idRol;

	@Column(name="nomb_rol")
	private String nombRol;

	public RolePK() {
	}
	public int getIdRol() {
		return this.idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getNombRol() {
		return this.nombRol;
	}
	public void setNombRol(String nombRol) {
		this.nombRol = nombRol;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePK)) {
			return false;
		}
		RolePK castOther = (RolePK)other;
		return 
			(this.idRol == castOther.idRol)
			&& this.nombRol.equals(castOther.nombRol);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRol;
		hash = hash * prime + this.nombRol.hashCode();
		
		return hash;
	}
}