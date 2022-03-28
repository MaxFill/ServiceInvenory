package ru.rt.som.si.client;

import org.springframework.util.Assert;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenHeaderRequestCallback implements WebServiceMessageCallback {
    private static final Logger LOGGER = Logger.getLogger(TokenHeaderRequestCallback.class.getName());
    private final String soapAction;
    private final String accessToken;

    public TokenHeaderRequestCallback(String soapAction, String accessToken) {
        this.soapAction = soapAction;
        this.accessToken = accessToken;
    }

    @Override
    public void doWithMessage(WebServiceMessage message) {
        try {
            Assert.isInstanceOf(SoapMessage.class, message);
            SoapMessage soapMessage = (SoapMessage) message;
            soapMessage.setSoapAction(this.soapAction);
            TransportContext context = TransportContextHolder.getTransportContext();
            WebServiceConnection connection = context.getConnection();
            HttpUrlConnection conn = (HttpUrlConnection) connection;
            conn.addRequestHeader("Authorization", "Bearer " + accessToken);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error during marshalling of the SOAP headers", ex.getMessage());
        }
    }
}
