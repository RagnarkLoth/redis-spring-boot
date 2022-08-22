package com.example.demo.convertidor;

import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertidorEntidades {

    public UsuarioModel convertirUsuario(UsuarioEntity usuarioEntity){

        return new UsuarioModel(usuarioEntity);

    }

    public List<UsuarioModel> convertirListaUsuarios(List<UsuarioEntity> listaUsuarioEntity)
    {

        List<UsuarioModel> listaUsuarioModel = new ArrayList<>();
        for (UsuarioEntity usuario : listaUsuarioEntity){

            listaUsuarioModel.add(new UsuarioModel(usuario));

        }

        return listaUsuarioModel;

    }
}
