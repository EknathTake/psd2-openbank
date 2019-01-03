package com.psd2.openbank.account.config;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${config.oauth2.accessTokenUri}")
	private String accessTokenUri;

	@Value("${config.oauth2.scope.payment}")
	private String paymentScope;

	@Value("${config.oauth2.scope.account}")
	private String accountScope;

	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/**")).build()
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Arrays.asList(securitySchema(), apiKey(), apiCookieKey())).apiInfo(getApiInfo());

	}

	@Bean
	public SecurityScheme apiKey() {
		return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
	}

	@Bean
	public SecurityScheme apiCookieKey() {
		return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("PSD2-OPEN-BANKING").description("")
				.termsOfServiceUrl("https://localhost:8800/")
				.contact(new Contact("Accenture Digital", "https://www.accenture.com/in-en/digital-index", ""))
				.license("Open Source").licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0").version("1.0.0")
				.build();
	}

	private OAuth securitySchema() {

		List<AuthorizationScope> authorizationScopeList = newArrayList();
		authorizationScopeList.add(new AuthorizationScope(paymentScope, "for payments"));
		authorizationScopeList.add(new AuthorizationScope(accountScope, "for accounts"));

		List<GrantType> grantTypes = newArrayList();
		GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
		grantTypes.add(passwordCredentialsGrant);

		return new OAuth("oauth2", authorizationScopeList, grantTypes);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {

		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");

		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
	}

}