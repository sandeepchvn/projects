package com.ty.ams.config;

import java.util.Arrays;

//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//@Configuration
//public class SwaggerConfiguration {
//
//	@Bean
//	public OpenAPI usersMicroserviceOpenAPI() {
//		Server localhost = new Server();
//		localhost.setUrl("http://hostname:8080");
//		localhost.setDescription("Development environment");
//		Contact contact = new Contact();
//		contact.setEmail("userapp@user.in");
//		contact.setName("User Application");
//		contact.setUrl("https://localhost");
//		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
//		Info info = new Info().title("User Application RESTful Web Service documentation").version("1.0").contact(contact)
//				.description("This API exposes endpoints to manage Application.")
//				.termsOfService("https://domainname/terms").license(mitLicense);
//		return new OpenAPI().info(info).servers(List.of(localhost));
//	}
//}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI usersMicroserviceOpenAPI() {
		Server localhost = new Server();
		localhost.setUrl("http://hostname:8080");
		localhost.setDescription("Development environment");
		Server server = new Server();
		server.setUrl("http://106.51.76.167:8080");
		server.setDescription("Server environment");
		Contact contact = new Contact();
		contact.setEmail("userapp@user.in");
		contact.setName("User Application");
		contact.setUrl("https://hostname/");
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
		Info info = new Info().title("User Application RESTful Web Service documentation").version("1.0")
				.contact(contact).description("This API exposes endpoints to manage Application.")
				.termsOfService("https://domainname/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(Arrays.asList(localhost, server));
	}

}
