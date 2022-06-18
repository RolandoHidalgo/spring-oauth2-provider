package cu.uci.cegel.oauth2server.controller;

import cu.uci.cegel.oauth2server.domain.Usuario;
import cu.uci.cegel.oauth2server.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Oauth2Endpoint {

    @Autowired
    DefaultTokenServices defaultTokenServices;
    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/onei/logout")
    @ResponseBody
    public void logout(HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            if (defaultTokenServices.revokeToken(tokenId)) {
                setUserAuth(username);
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/onei/logout/{username}")
    @ResponseBody
    public void logoutUser(@PathVariable("username") String username) {
        setUserAuth(username);
    }

    private void setUserAuth(String username) {
        Usuario byUsername = usuarioRepository.findByUsername(username);
        if (!byUsername.isAdmin()) {
            byUsername.setAuth(false);
            usuarioRepository.save(byUsername);
        }
    }
}
