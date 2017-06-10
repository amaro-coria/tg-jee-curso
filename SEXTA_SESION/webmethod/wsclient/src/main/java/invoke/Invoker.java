package invoke;

import tecgurus.catservice._1.*;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Amaro on 10/06/2017.
 */
public class Invoker {

    public void invoke(){
        try {
            String endpointAddress ="http://localhost:18070/core/catService?wsdl";
            System.out.println("Calling to:" + endpointAddress);
            URL wsdlLocation = null;
            try {
                wsdlLocation = new URL(endpointAddress);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            QName serviceName = new QName("http://www.tecgurus.net/catService/1.0",
                   "catService");
            javax.xml.ws.Service s = CatService.create(wsdlLocation,
                    serviceName);
            CatServicePortType port = s
                    .getPort(CatServicePortType.class);
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
            CatGralRequest request = new CatGralRequest();
            AuditData ad = new AuditData();
            ad.setIp("127.0.0.1");
            ad.setUserName("jamaro");
            request.setAuditData(ad);
            CatGralResponse response = port.listarCatalogoOperation(request);
            List<CatGralData> listData = response.getCatGralInfo();
            for (CatGralData d: listData) {
                System.out.println(d.getDescComp());
                System.out.println(d.getDescCort());
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


}
