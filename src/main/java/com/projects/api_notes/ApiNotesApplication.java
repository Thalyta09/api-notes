package com.projects.api_notes;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiNotesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiNotesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiNotesApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi(@Value("${application-description}") String appDescription,
								 @Value("${application-version}") String appVersion) {

		return new OpenAPI()
				.info(new Info()
						.title("Projeto API de anotações")
						.version(appVersion)
						.description(appDescription)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.addServersItem(new Server().url("http://localhost:8080").description("Base URL"))
				//.addServersItem(new Server().url("https://personapi-test.herokuapp.com/").description("Heroku URL"))
				.externalDocs(new ExternalDocumentation().description("Saiba mais em").url("http://swagger.io"));
	}
}