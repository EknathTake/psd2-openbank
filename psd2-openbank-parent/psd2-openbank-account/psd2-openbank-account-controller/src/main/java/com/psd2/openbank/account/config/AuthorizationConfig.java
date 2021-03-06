package com.psd2.openbank.account.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.psd2.openbank.account.entity.RoleEntity;
import com.psd2.openbank.account.entity.UserEntity;
import com.psd2.openbank.account.service.TokenBlackListService;
import com.psd2.openbank.account.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	@Value("${config.oauth2.clientSecret}")
	private String clientSecret;

	@Value("${config.oauth2.clientID}")
	private String clientID;

	@Value("${config.oauth2.scope.payment}")
	private String paymentScope;

	@Value("${config.oauth2.scope.account}")
	private String accountScope;

	@Value("${security.oauth2.client.grantType}")
	private String grantType;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).tokenServices(tokenServices())
				.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer
				// we're allowing access to the token only for clients with
				// 'ROLE_TRUSTED_CLIENT' authority
				.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
				.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientID).authorizedGrantTypes(grantType, "password", "refresh_token")
				.authorities(RoleEntity.ROLE_TRUSTED_CLIENT.toString()).scopes(paymentScope, accountScope)
				.resourceIds(resourceId).accessTokenValiditySeconds(300).refreshTokenValiditySeconds(30000)
				.secret(clientSecret).and().withClient("register-app").authorizedGrantTypes(grantType)
				.authorities(RoleEntity.ROLE_REGISTER.toString()).scopes("registerUser").accessTokenValiditySeconds(10)
				.refreshTokenValiditySeconds(10).resourceIds(resourceId).secret(clientSecret);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());

	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"),
				"mypass".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
		return converter;
	}

	@Autowired
	private TokenBlackListService blackListService;

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		MyTokenService tokenService = new MyTokenService(blackListService);
		tokenService.setTokenStore(tokenStore());
		tokenService.setSupportRefreshToken(true);
		tokenService.setTokenEnhancer(accessTokenConverter());
		return tokenService;
	}

	static class MyTokenService extends DefaultTokenServices {
		private Logger logger = LoggerFactory.getLogger(MyTokenService.class);

		private TokenBlackListService blackListService;

		public MyTokenService(TokenBlackListService blackListService) {
			this.blackListService = blackListService;
		}

		@Override
		public OAuth2AccessToken readAccessToken(String accessToken) {
			return super.readAccessToken(accessToken);
		}

		@Override
		public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
			OAuth2AccessToken token = super.createAccessToken(authentication);
			UserEntity account = (UserEntity) authentication.getPrincipal();
			String jti = (String) token.getAdditionalInformation().get("jti");

			blackListService.addToEnabledList(account.getId(), jti, token.getExpiration().getTime());
			return token;
		}

		@Override
		public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest)
				throws AuthenticationException {
			logger.info("refresh token:" + refreshTokenValue);
			String jti = tokenRequest.getRequestParameters().get("jti");
			try {
				if (jti != null)
					if (blackListService.isBlackListed(jti))
						return null;

				OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
				blackListService.addToBlackList(jti);
				return token;
			} catch (TokenBlackListService.TokenNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
