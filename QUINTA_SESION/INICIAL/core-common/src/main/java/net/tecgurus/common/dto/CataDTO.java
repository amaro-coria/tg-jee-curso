package net.tecgurus.common.dto;

import java.io.Serializable;
import java.util.Date;

public class CataDTO implements Serializable{

	private int idCat;
	private String dscComCat;
	private String dscCorCat;
	private Date fchCre;
	private Date fchMod;
	private int idEstCat;
	
	
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getDscComCat() {
		return dscComCat;
	}
	public void setDscComCat(String dscComCat) {
		this.dscComCat = dscComCat;
	}
	public String getDscCorCat() {
		return dscCorCat;
	}
	public void setDscCorCat(String dscCorCat) {
		this.dscCorCat = dscCorCat;
	}
	public Date getFchCre() {
		return fchCre;
	}
	public void setFchCre(Date fchCre) {
		this.fchCre = fchCre;
	}
	public Date getFchMod() {
		return fchMod;
	}
	public void setFchMod(Date fchMod) {
		this.fchMod = fchMod;
	}
	public int getIdEstCat() {
		return idEstCat;
	}
	public void setIdEstCat(int idEstCat) {
		this.idEstCat = idEstCat;
	}
	
	
	
}
