package cu.uci.cegel.oauth2server.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE arquitectura.dusuario SET is_auth=false WHERE id > :ids",
            nativeQuery = true)
    void cleanAuth(@Param("ids") Integer ids);
}
