package com.example.demo.models;

import com.example.demo.entitys.UsuarioEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import java.io.Serializable;

//@RedisHash("usuario") PARA INDICAR QUE ES UN REPOSITORIO REDIS
@NoArgsConstructor
@Getter
@Setter
public class UsuarioModel implements Serializable {

    public UsuarioModel(UsuarioEntity usuarioEntity){

        this.idUsuario = usuarioEntity.getIdUsuario();
        this.nombreUsuario = usuarioEntity.getNombreUsuario();
        this.edadUsuario = usuarioEntity.getEdadUsuario();
        this.numeroTelefono = usuarioEntity.getNumeroTelefono();

    }

    private Long idUsuario;

    private String nombreUsuario;

    private Integer edadUsuario;

    private String numeroTelefono;

}
