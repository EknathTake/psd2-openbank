package com.psd2.openbank.account.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiDocket() {

		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> parameters = new ArrayList<>();
		tokenPar.name("xmall-Token").description("token").defaultValue("admin").modelRef(new ModelRef("string"))
				.parameterType("header").required(false).build();
		parameters.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/**")).build().apiInfo(getApiInfo());

	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("PSD2-OPEN-BANK", "DESCIPRION", "1.0.0.VERSION", "http://localhost:8080/terms-of-service",
				null, "http://localhost:8080/license", "LICENSE URL", Collections.emptyList());
	}
}