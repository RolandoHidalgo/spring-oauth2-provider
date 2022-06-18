package cu.uci.cegel.oauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author cegel
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dpermiso", schema = "arquitectura")
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dpermiso_seq")
    @SequenceGenerator(name = "dpermiso_seq", sequenceName = "dpermiso_id_seq", initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "permiso")
    private String permiso;
    @Column(name = "activo")
    private Boolean activo;
//    @ManyToMany(mappedBy = "permisos")
//    private List<Rol> roles;
}
