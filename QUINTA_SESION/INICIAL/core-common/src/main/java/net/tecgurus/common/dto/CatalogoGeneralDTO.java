package net.tecgurus.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoGeneralDTO implements Serializable {

	@XmlElement
	private int idCat;
	@XmlElement
	private String dscComCat;
	@XmlElement
	private String dscCorCat;
	@XmlElement
	private Date fchCre;
	@XmlElement
	private Date fchMod;
	@XmlElement
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
