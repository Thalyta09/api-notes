package com.projects.api_notes;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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

		final String securitySchemeName = "bearerAuth";
		SecurityScheme securityScheme = new SecurityScheme()
				.name(securitySchemeName)
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT")
				.description("Token JWT obtido na rota de login.");

		// Define a necessidade de segurança (Security Requirement)
		SecurityRequirement securityRequirement = new SecurityRequirement()
				.addList(securitySchemeName);

		return new OpenAPI()
				.info(new Info()
						.title("Projeto API de anotações")
						.version(appVersion)
						.description(appDescription)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.addSecurityItem(securityRequirement) // Adiciona o requisito de segurança globalmente
				.components(new Components()
						.addSecuritySchemes(securitySchemeName, securityScheme) // Adiciona o esquema aos componentes
				)
				.addServersItem(new Server().url("http://localhost:8080").description("Base URL"))
				.externalDocs(new ExternalDocumentation().description("Saiba mais em").url("http://swagger.io"));
	}
}