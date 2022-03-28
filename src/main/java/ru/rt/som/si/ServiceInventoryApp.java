package ru.rt.som.si;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import ru.rt.som.si.client.KeycloakClient;

@SpringBootApplication
@RestController
public class ServiceInventoryApp {

	/*
	for test start app and goto http://localhost:8080
	 */
	@GetMapping("/")
	String onlyForRemoteTest() {
		KeycloakClient client = new KeycloakClient();
		client.init();
		String cfsId = client.doTestRequest("DK225469888");
		return "SOAP request with auth keycloak completed! cfsId=" + cfsId;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceInventoryApp.class, args);
	}
}