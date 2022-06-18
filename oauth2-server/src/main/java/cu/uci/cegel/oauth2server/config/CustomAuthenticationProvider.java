package cu.uci.cegel.oauth2server.config;

import cu.uci.cegel.oauth2server.domain.CustomUserDetailsService;
import cu.uci.cegel.oauth2server.domain.CustomUserFactory;
import cu.uci.cegel.oauth2server.domain.Usuario;
import cu.uci.cegel.oauth2server.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CustomUserFactory customUserFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // Find user
        String username = token.getName();
        Usuario user = usuarioRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(username);

        UserDetails userDetails = customUserFactory.createFromUser(user);

        // Compare password and credentials of authentication.
        if (!passwordEncoder.matches(token.getCredentials().toString(), userDetails.getPassword())) {
            if (user.getContador().toString().equals("3")){
                user.setEnabled(false);
                user.setContador(Long.valueOf(0));
                usuarioRepository.save(user);
                throw new DisabledException("Su cuenta ha alcanzado el total de intentos, queda deshabilitada temporalmente. Contacte a su administrador.");
            }else  if (userDetails.isEnabled()){
                user.setContador(user.getContador()+1);
                usuarioRepository.save(user);
                Long pepe = (3-user.getContador());
                throw new DisabledException("Corrija su clave, quedan ".concat(pepe.toString()).concat(" intentos."));
            }else {
                throw new DisabledException("Su cuenta fue deshabilitada, contacte a su administrador.");
            }
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("");
        } else if (user.isAuth() && !user.isAdmin()) {
            throw new AccountInUseExeception("Su cuenta se encuentra en uso.");
        } else{
            user.setAuth(true);
            user.setContador(Long.valueOf(0));
            usuarioRepository.save(user);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
