package cu.uci.cegel.oauth2server.domain;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Rol, Long> {
    Rol findByRol(String rol);
}
