package ru.rt.som.si.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.rt.oms.ObjectFactory;
import ru.rt.oms.GetCfsRequest;
import ru.rt.oms.GetCfsResponse;
import ru.rt.oms.GetRfsRequest;
import ru.rt.oms.GetRfsResponse;
import ru.rt.som.si.client.KeycloakClient;
import ru.rt.som.si.dataaccesslayer.controller.CfsItemController;
import ru.rt.som.si.dataaccesslayer.controller.RfsItemController;
import ru.rt.som.si.dataaccesslayer.model.CfsItem;
import ru.rt.som.si.dataaccesslayer.model.RfsItem;

import javax.annotation.security.RolesAllowed;
import java.util.logging.Level;
import java.util.logging.Logger;

@Endpoint
public class WebServiceEndpoint {
    private static final Logger LOGGER = Logger.getLogger(WebServiceEndpoint.class.getName());
    private static final String NAMESPACE_URI = "http://oms.rt.ru";

    @Autowired
    private CfsItemController cfsItemController;
    @Autowired
    private RfsItemController rfsItemController;

    @RolesAllowed("user")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCfsRequest")
    @ResponsePayload
    public GetCfsResponse getCfs(@RequestPayload GetCfsRequest request) {
        GetCfsResponse response = new ObjectFactory().createGetCfsResponse();
        response.setOriginator(request.getReceiver());
        response.setReceiver(request.getOriginator());

        //demo
        CfsItem cfsItem = cfsItemController.getCfsById(request.getCfsId());
        if (cfsItem == null) {
            LOGGER.log(Level.INFO, "CfsItem not found! Id={0}", request.getCfsId());
            //ToDo нужно сформировать корректный ответ если не найдено!
            return response;
        }
        response.setCfsId(cfsItem.getCfsId());
        response.setCfsSpecId(cfsItem.getSpecId());
        response.setCfsStatus(cfsItem.getCfsStatus());
        response.setSubscriptionId(cfsItem.getSubscriptionItem().getSubscriptionId());
        return response;
    }

    @RolesAllowed("admin")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRfsRequest")
    @ResponsePayload
    public GetRfsResponse getRfs(@RequestPayload GetRfsRequest request) {
        GetRfsResponse response = new ObjectFactory().createGetRfsResponse();
        response.setOriginator(request.getReceiver());
        response.setReceiver(request.getOriginator());
        RfsItem rfsItem = rfsItemController.getRfsById(request.getRfsId());
        if (rfsItem == null) {
            LOGGER.log(Level.INFO, "RfsItem not found! Id={0}", request.getRfsId());
            //ToDo нужно сформировать корректный ответ если не найдено!
            return response;
        }
        response.setRfsId(rfsItem.getRfsId());
        response.setRfsSpecId(rfsItem.getRfsSpecId());
        return response;
    }
}
