package cu.uci.cegel.oauth2server.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permiso, Long> {
    Permiso findByPermiso(String permiso);
}
