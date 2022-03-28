package ru.rt.som.si.client;

import org.keycloak.admin.client.Keycloak;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.rt.oms.GetCfsRequest;
import ru.rt.oms.GetCfsResponse;

public class KeycloakClient extends WebServiceGatewaySupport {

    private Jaxb2Marshaller marshaller;

    public void init() {
        setDefaultUri("http://localhost:8080/oms/ws/newSI");
        marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("ru.rt.oms");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    public String doTestRequest(String cfsId) {
        Keycloak keycloak = Keycloak.getInstance(
                "http://10.42.114.95:8081/auth",
                "SOM_NW", // Указать мастер области
                "filatovms", // Администратор аккаунта
                "qwerty12345", // Пароль администратора
                "login-app");

        String accessTokenStr = keycloak.tokenManager().getAccessTokenString();
        GetCfsResponse response = getRemoteCfs(cfsId, accessTokenStr);
        if (response != null) {
            return response.getCfsId();
        }
        return "cfs not found!";
    }

    private GetCfsResponse getRemoteCfs(String cfsId, String accessToken) {
        GetCfsRequest cfsRequest = new GetCfsRequest();
        cfsRequest.setCfsId(cfsId);
        return (GetCfsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(cfsRequest, new TokenHeaderRequestCallback("#POST", accessToken));
    }
}
