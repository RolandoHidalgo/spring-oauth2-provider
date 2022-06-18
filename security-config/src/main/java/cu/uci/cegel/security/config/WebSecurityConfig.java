package cu.uci.cegel.security.config;

import cu.uci.cegel.security.utils.IPAddressValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Bean
    public IPAddressValidator ipAddressValidator() {
        return new IPAddressValidator();
    }
}
