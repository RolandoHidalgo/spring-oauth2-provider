package cu.uci.cegel.oauth2server;

import cu.uci.cegel.oauth2server.domain.CustomUserDetailsService;
import cu.uci.cegel.oauth2server.domain.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.util.Locale;

@RestController
@SpringBootApplication
public class Oauth2ServerApplication {

    private final
    CustomUserDetailsService userDetailsService;

    @Autowired
    public Oauth2ServerApplication(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:messages_es_ES");
        userDetailsService.cleanAuth();
        return messageSource;
    }
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }}
