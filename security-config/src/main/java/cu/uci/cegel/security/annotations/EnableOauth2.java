package cu.uci.cegel.security.annotations;

import cu.uci.cegel.security.config.CorsFilter;
import cu.uci.cegel.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({CorsFilter.class, WebSecurityConfig.class})
public @interface EnableOauth2 {
}
