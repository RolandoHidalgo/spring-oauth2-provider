package cu.uci.cegel.oauth2server.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final CustomUserFactory customUserFactory;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository, CustomUserFactory customUserFactory) {
        this.usuarioRepository = usuarioRepository;
        this.customUserFactory = customUserFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return customUserFactory.createFromUser(user);
    }

    public void cleanAuth(){
        usuarioRepository.cleanAuth(0);
    }
}
