package net.tecgurus.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cte")
	private int idCte;

	@Column(name="ape_mat_cte")
	private String apeMatCte;

	@Column(name="ape_pat_cte")
	private String apePatCte;

	@Column(name="dir_cte")
	private String dirCte;

	@Column(name="nom_cte")
	private String nomCte;

	@Column(name="tel_cte")
	private String telCte;

	//bi-directional many-to-one association to Catalogo_General
	@ManyToOne
	@JoinColumn(name="id_edo_cte")
	private Catalogo_General catalogoGeneral;

	public Cliente() {
	}

	public int getIdCte() {
		return this.idCte;
	}

	public void setIdCte(int idCte) {
		this.idCte = idCte;
	}

	public String getApeMatCte() {
		return this.apeMatCte;
	}

	public void setApeMatCte(String apeMatCte) {
		this.apeMatCte = apeMatCte;
	}

	public String getApePatCte() {
		return this.apePatCte;
	}

	public void setApePatCte(String apePatCte) {
		this.apePatCte = apePatCte;
	}

	public String getDirCte() {
		return this.dirCte;
	}

	public void setDirCte(String dirCte) {
		this.dirCte = dirCte;
	}

	public String getNomCte() {
		return this.nomCte;
	}

	public void setNomCte(String nomCte) {
		this.nomCte = nomCte;
	}

	public String getTelCte() {
		return this.telCte;
	}

	public void setTelCte(String telCte) {
		this.telCte = telCte;
	}

	public Catalogo_General getCatalogoGeneral() {
		return this.catalogoGeneral;
	}

	public void setCatalogoGeneral(Catalogo_General catalogoGeneral) {
		this.catalogoGeneral = catalogoGeneral;
	}

}