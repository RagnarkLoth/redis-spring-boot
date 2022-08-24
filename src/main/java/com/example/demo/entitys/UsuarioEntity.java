package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.profile.Fetch;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    public UsuarioEntity(String nombreUsuario, Integer edadUsuario, String numeroTelefono, CarroEntity carroEntity) {
        this.nombreUsuario = nombreUsuario;
        this.edadUsuario = edadUsuario;
        this.numeroTelefono = numeroTelefono;
        this.carroFk = carroEntity;
    }

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombreusuario")
    private String nombreUsuario;

    @Column(name = "edadusuario")
    private Integer edadUsuario;

    @Column(name = "numerotelefono")
    private String numeroTelefono;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idCarro")
    @JsonBackReference
    private CarroEntity carroFk;

}
