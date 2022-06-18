package cu.uci.cegel.oauth2server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private Long id;
    private Dpa dpa;
    private Boolean inicial;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    CustomUser(String username, Long id, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Dpa dpa, Boolean inicial) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.dpa = dpa;
        this.inicial = inicial;
    }

    public Long getId() {
        return this.id;
    }

    public Boolean isInicial() { return this.inicial;
    }

    public Dpa getDpa() {
        return this.dpa;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }
}
