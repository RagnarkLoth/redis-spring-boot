package com.example.demo.convertidor;

import com.example.demo.entitys.CarroEntity;
import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.CarroModel;
import com.example.demo.models.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertidorEntidades {

    public UsuarioModel convertirUsuario(UsuarioEntity usuarioEntity){

        return new UsuarioModel(usuarioEntity);

    }

    public CarroModel convertirCarro(CarroEntity carroEntity){

        return new CarroModel(carroEntity);

    }

    public List<UsuarioModel> convertirListaUsuarios(List<UsuarioEntity> listaUsuarioEntity)
    {

        List<UsuarioModel> listaUsuarioModel = new ArrayList<>();
        for (UsuarioEntity usuario : listaUsuarioEntity){

            listaUsuarioModel.add(new UsuarioModel(usuario));

        }

        return listaUsuarioModel;

    }

    public List<CarroModel> convertirListaCarros(List<CarroEntity> listaCarroEntity)
    {

        List<CarroModel> listaCarroModel = new ArrayList<>();
        for (CarroEntity carro : listaCarroEntity){

            listaCarroModel.add(new CarroModel(carro));

        }

        return listaCarroModel;

    }

}
