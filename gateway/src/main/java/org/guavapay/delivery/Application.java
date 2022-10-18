package org.guavapay.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		String authUri = uriConfiguration.getAuthUri();
		String orderUri = uriConfiguration.getOrderUri();
		return builder.routes()
			.route(p -> p
				.path("/rest-auth/**")
				.uri(authUri))
			.route(p -> p
				.path("/rest-delivery/**")
				.uri(orderUri))
			.build();
	}

}

@ConfigurationProperties
class UriConfiguration {
	
	private String authUri = "http://auth:8081";
	private String orderUri = "http://delivery:8082";

	public String getAuthUri() {
		return authUri;
	}

	public void setAuthUri(String authUri) {
		this.authUri = authUri;
	}

	public String getOrderUri() {
		return orderUri;
	}

	public void setOrderUri(String orderUri) {
		this.orderUri = orderUri;
	}
}
