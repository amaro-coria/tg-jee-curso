package net.tecgurus.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Catalogo_General database table.
 * 
 */
@Entity
@NamedQuery(name="Catalogo_General.findAll", query="SELECT c FROM Catalogo_General c")
public class Catalogo_General implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int idCat;

	@Column(name="dsc_com_cat")
	private String dscComCat;

	@Column(name="dsc_cor_cat")
	private String dscCorCat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fch_cre")
	private Date fchCre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fch_mod")
	private Date fchMod;

	@Column(name="id_est_cat")
	private int idEstCat;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="catalogoGeneral")
	private List<Cliente> clientes;

	public Catalogo_General() {
	}

	public int getIdCat() {
		return this.idCat;
	}

	public void setIdCat(int idCat) {
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

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setCatalogoGeneral(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setCatalogoGeneral(null);

		return cliente;
	}

}