
package tecgurus.catservice._1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "catServicePortType", targetNamespace = "http://www.tecgurus.net/catService/1.0")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CatServicePortType {


    /**
     * 
     * @param catGralRequest
     * @return
     *     returns net.tecgurus.catservice._1.CatGralResponse
     */
    @WebMethod(action = "http://www.tecgurus.net/catService/listarCatalogoOperation")
    @WebResult(name = "cat_gral_response", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_response")
    public CatGralResponse listarCatalogoOperation(
            @WebParam(name = "cat_gral_request", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_request")
                    CatGralRequest catGralRequest);

    /**
     *
     * @param catGralAltaRequest
     * @return
     *     returns net.tecgurus.catservice._1.CatGralAltaResponse
     */
    @WebMethod(action = "http://www.tecgurus.net/catService/altaCatalogoOperation")
    @WebResult(name = "cat_gral_alta_response", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_alta_response")
    public CatGralAltaResponse altaCatalogoOperation(
            @WebParam(name = "cat_gral_alta_request", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_alta_request")
                    CatGralAltaRequest catGralAltaRequest);

    /**
     *
     * @param catGralModiRequest
     * @return
     *     returns net.tecgurus.catservice._1.CatGralModiResponse
     */
    @WebMethod(action = "http://www.tecgurus.net/catService/modificacionCatalogoOperation")
    @WebResult(name = "cat_gral_modi_response", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_modi_response")
    public CatGralModiResponse modificacionCatalogoOperation(
            @WebParam(name = "cat_gral_modi_request", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_modi_request")
                    CatGralModiRequest catGralModiRequest);

    /**
     *
     * @param catGralBajaRequest
     * @return
     *     returns net.tecgurus.catservice._1.CatGralBajaResponse
     */
    @WebMethod(action = "http://www.tecgurus.net/catService/bajaCatalogoOperation")
    @WebResult(name = "cat_gral_baja_response", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_baja_response")
    public CatGralBajaResponse bajaCatalogoOperatioon(
            @WebParam(name = "cat_gral_baja_request", targetNamespace = "http://www.tecgurus.net/catService/1.0", partName = "cat_gral_baja_request")
                    CatGralBajaRequest catGralBajaRequest);

}
