package cu.uci.cegel.oauth2server.config;

import cu.uci.cegel.oauth2server.domain.CustomUser;
import cu.uci.cegel.oauth2server.domain.Usuario;
import cu.uci.cegel.oauth2server.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class Oauth2ExceptionTranslatorConfiguration {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TokenStore tokenStore;

    @Bean
    public WebResponseExceptionTranslator oauth2ResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                if (e instanceof InvalidTokenException) {

                    InvalidTokenException invalidTokenException = (InvalidTokenException) e;
                    String message = invalidTokenException.getMessage();
                    if (message.contains("Invalid refresh token (expired)")) {
                        takeActionsForUserRefreshTokenExpired(message.split(": ")[1]);
                    }
                }
                return super.translate(e);

            }
        };
    }

    private void takeActionsForUserRefreshTokenExpired(String refreshTokenString) {
        OAuth2RefreshToken rt = tokenStore.readRefreshToken(refreshTokenString);
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthenticationForRefreshToken(rt);
        String userName = (String) oAuth2Authentication.getUserAuthentication().getPrincipal();
        logOutUser(userName);
    }

    private void logOutUser(String username) {
        Usuario byUsername = usuarioRepository.findByUsername(username);
        byUsername.setAuth(false);
        usuarioRepository.save(byUsername);
    }
}
