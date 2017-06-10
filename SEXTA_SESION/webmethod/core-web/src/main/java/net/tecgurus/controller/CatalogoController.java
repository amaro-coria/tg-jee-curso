package net.tecgurus.controller;

import lombok.Data;
import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;
import net.tecgurus.util.Constants;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ViewScoped
@ManagedBean(name = "catalogoCtrl")
@Data
public class CatalogoController extends GenericController implements Serializable {

    private List<CataDTO> listElements;
    @EJB
    private CatalogoBusiness business;

    private String descCorta;
    private String descComp;
    private Date fchCrea;
    private Date fchModi;
    private Integer idEstatus;
    private CataDTO currentElement;
    private CataDTO currentEditElement;
    // inputs para campos de edicion
    private String editDescCorta;
    private String editDescComp;
    private Date editFecha;

    @PostConstruct
    private void init() {
        currentElement = new CataDTO();
        listElements = business.findAllDTO_1();
    }

    public String save() {
        CataDTO dto = new CataDTO();
        dto.setDscComCat(descComp);
        dto.setDscCorCat(descCorta);
        dto.setFchCre(fchCrea);
        dto.setIdEstCat(1);
        business.createNewRecord(dto);
        super.addInfoMessage(Constants.MESSAGE_SUCCESS, Constants.MESSAGE_SAVED_DETAIL);
        listElements = business.findAllDTO_1();
        return "";
    }

    public void preEdit(){
        currentEditElement = currentElement;
    }

    public String edit() {
        if(!editDescComp.trim().isEmpty()){
            currentEditElement.setDscComCat(editDescComp);
        }if(!editDescCorta.trim().isEmpty()){
            currentEditElement.setDscCorCat(editDescCorta);
        }
        currentEditElement.setFchMod(now());
        business.updateRecord(currentEditElement);
        currentEditElement = null;
        super.addInfoMessage(Constants.MESSAGE_SUCCESS, Constants.MESSAGE_SAVED_DETAIL);
        listElements = business.findAllDTO_1();
        return "";
    }

    public String delete() {
        currentElement.setIdEstCat(2);
        currentElement.setFchMod(now());
        business.updateRecord(currentElement);
        super.addInfoMessage(Constants.MESSAGE_SUCCESS, Constants.MESSAGE_SAVED_DETAIL);
        listElements = business.findAllDTO_1();
        return "";
    }

    public String cancel() {
        super.addInfoMessage(Constants.MESSAGE_CANCEL);
        return "";
    }


}
