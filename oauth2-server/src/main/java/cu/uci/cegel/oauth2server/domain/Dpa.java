package cu.uci.cegel.oauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cegel
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nd_p_a", schema = "public")
public class Dpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "id_nd_p_a")
    private Long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cod_d_p_a")
    private String codigodpa;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "idpadre")
    private Long idpadre;
}
