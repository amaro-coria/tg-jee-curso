package net.tecgurus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the catalogo_general database table.
 * 
 */
@Entity
@Table(name = "Catalogo_General")
@NamedQueries({ @NamedQuery(name = "CatalogoGeneral.findAll", query = "SELECT c FROM CatalogoGeneral c"),
		@NamedQuery(name = "CatalogoGeneral.findAllActive", 
		query = "SELECT c FROM CatalogoGeneral c WHERE c.idEstCat = 1"),
		@NamedQuery(name = "CatalogoGeneral.findAllByState", 
		query = "SELECT c FROM CatalogoGeneral c WHERE c.idEstCat = :idEstCat") })
@NamedNativeQuery(name = "CatalogoGeneral.nativeSelect", query = "select * from catalogo_general", resultClass = CatalogoGeneral.class)
public class CatalogoGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cat")
	private Integer idCat;

	@Column(name = "dsc_com_cat")
	private String dscComCat;

	@Column(name = "dsc_cor_cat")
	private String dscCorCat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fch_cre")
	private Date fchCre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fch_mod")
	private Date fchMod;

	@Column(name = "id_est_cat")
	private int idEstCat;

	public CatalogoGeneral() {
	}

	public Integer getIdCat() {
		return this.idCat;
	}

	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}

	public String getDscComCat() {
		return this.dscComCat;
	}

	public void setDscComCat(String dscComCat) {
		this.dscComCat = dscComCat;
	}

	public String getDscCorCat() {
		return this.dscCorCat;
	}

	public void setDscCorCat(String dscCorCat) {
		this.dscCorCat = dscCorCat;
	}

	public Date getFchCre() {
		return this.fchCre;
	}

	public void setFchCre(Date fchCre) {
		this.fchCre = fchCre;
	}

	public Date getFchMod() {
		return this.fchMod;
	}

	public void setFchMod(Date fchMod) {
		this.fchMod = fchMod;
	}

	public int getIdEstCat() {
		return this.idEstCat;
	}

	public void setIdEstCat(int idEstCat) {
		this.idEstCat = idEstCat;
	}

}