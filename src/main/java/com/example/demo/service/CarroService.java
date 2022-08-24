package com.example.demo.service;

import com.example.demo.convertidor.ConvertidorEntidades;
import com.example.demo.entitys.CarroEntity;
import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.CarroModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.CarroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CarroService {

    @Autowired
    private ConvertidorEntidades convertidorEntidades;

    @Autowired
    private CarroRepository carroRepository;

    public CarroModel crearCarro(CarroEntity carroEntity){
        //convertidorEntidades.convertirUsuario(usuarioRepository.save(usuarioEntity));

        CarroModel carroModel = convertidorEntidades.convertirCarro(carroRepository.save(carroEntity));
        return carroModel;
    }

    public List<CarroModel> listarCarros(){

        return convertidorEntidades.convertirListaCarros(carroRepository.findAll());

    }

    public CarroModel listarCarroID(Long idCarro){
        //log.warn(idUsuario);
        return convertidorEntidades.convertirCarro(carroRepository.findById(idCarro).orElse(null));
        //return gson.fromJson(redisTemplate.opsForValue().get(idUsuario.toString()), UsuarioModel.class);
    }

    public void eliminarUsuario(Long id){
        CarroEntity carro = carroRepository.findById(id).orElse(null);
        carroRepository.delete(carro);
    }

    public CarroModel actualizarCarro(CarroEntity carroEntity){
        //redisTemplate.opsForValue().set(usuarioEntity.getIdUsuario().toString(), gson.toJson(usuarioEntity));
        return convertidorEntidades.convertirCarro(carroRepository.save(carroEntity));
    }

}
