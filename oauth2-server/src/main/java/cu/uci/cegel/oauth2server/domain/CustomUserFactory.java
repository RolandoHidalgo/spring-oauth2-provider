package cu.uci.cegel.oauth2server.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserFactory {

    public CustomUser createFromUser(Usuario usuario) {
        return new CustomUser(
                usuario.getUsername(),
                usuario.getId(),
                usuario.getPassword(),
                usuario.isEnabled(),
                usuario.isExpired(),
                usuario.isCredentialsexpired(),
                usuario.isLocked(),
                getAuthoritiesFromUser(usuario),
                usuario.getDpa(),
                usuario.isInicial()
        );
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromUser(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<Rol> roles = usuario.getRoles();
        for (Rol rol : roles) {
//            authorities.add(new SimpleGrantedAuthority(rol.getRol()));
            rol.getPermisos()
                    .stream()
                    .map(permiso -> new SimpleGrantedAuthority(permiso.getPermiso()))
                    .forEach(authorities::add);
        }
        usuario.getPermisos()
                .stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getPermiso()))
                .forEach(authorities::add);

        return authorities;
    }
}
