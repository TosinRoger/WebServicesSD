package br.com.tosin.sd.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

/**
 * Instanciado pelo jsersey, configura map para o spring.
 * @author tosin
 *
 */
public class MyApplication extends Application {

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotações REST.
		properties
				.put("jersey.config.server.provider.packages", "br.com.tosin.sd.webservices");
		return properties;
	}
}
