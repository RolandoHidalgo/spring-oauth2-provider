package cu.uci.cegel.oauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST, "/onei/logout").permitAll()
                .and()
                .formLogin().permitAll();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        System.out.println("asdsd@@@@@@@@@@@@@@");
        config
                .resourceId(SecurityConstants.OAUTH2_RESOURCE_ID);
    }
}
