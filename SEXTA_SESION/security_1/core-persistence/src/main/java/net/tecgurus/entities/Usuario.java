package net.tecgurus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private byte enabled;

	@Column(name="nomb_usuario")
	private String nombUsuario;

	@Column(name="pwd_usuario")
	private String pwdUsuario;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getNombUsuario() {
		return this.nombUsuario;
	}

	public void setNombUsuario(String nombUsuario) {
		this.nombUsuario = nombUsuario;
	}

	public String getPwdUsuario() {
		return this.pwdUsuario;
	}

	public void setPwdUsuario(String pwdUsuario) {
		this.pwdUsuario = pwdUsuario;
	}

}