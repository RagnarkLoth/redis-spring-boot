package com.example.demo.entitys;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

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

}
