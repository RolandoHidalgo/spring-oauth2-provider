package cu.uci.cegel.oauth2server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dusuario", schema = "arquitectura")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "username", unique = true)
    private String username;
    @Basic(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    private boolean enabled;
    @Basic(optional = false)
    @Column(name = "non_credential_expired")
    private boolean credentialsexpired;
    @Basic(optional = false)
    @Column(name = "non_expired")
    private boolean expired;
    @Basic(optional = false)
    @Column(name = "non_locked")
    private boolean locked;
    @Column(name = "is_auth")
    private boolean isAuth;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "contador")
    private Long contador;
    @Column(name = "inicial")
    private boolean inicial;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "dusuario_permiso", schema = "arquitectura",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id", referencedColumnName = "id"))
    private Set<Permiso> permisos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "dusuario_rol", schema = "arquitectura", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Set<Rol> roles;

    @ManyToOne
    @JoinColumn(name = "dpa")
    private Dpa dpa;

}
