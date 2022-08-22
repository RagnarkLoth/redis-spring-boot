package com.example.demo.service;

import com.example.demo.cache.CacheConfig;
import com.example.demo.convertidor.ConvertidorEntidades;
import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class UsuarioService {

    @Autowired
    private ConvertidorEntidades convertidorEntidades;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    Gson gson;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //@Cacheable(value = "cache", key = "#usuarioEntity.nombreUsuario", unless = "#result == null ")
    public UsuarioModel crearUsuario(UsuarioEntity usuarioEntity){

        UsuarioModel usuarioModel = convertidorEntidades.convertirUsuario(usuarioRepository.save(usuarioEntity));
        redisTemplate.opsForValue().set(usuarioEntity.getIdUsuario().toString(), gson.toJson(usuarioEntity));
        redisTemplate.expire(usuarioEntity.getIdUsuario().toString(), 1, TimeUnit.MINUTES);
        return usuarioModel;
    }

    public List<UsuarioModel> listarUsuarios(){

       return convertidorEntidades.convertirListaUsuarios(usuarioRepository.findAll());


    }

    //ZONA CACHE
    //EL "unless" indica que no queremos que se coloque en el cache
    //@Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    //PARA PONER ALGO EN LA CACHE USAMOS
    //"value" SERA EL COMO SE LLAMARA EN LA CACHE
    //"key" ES EL COMO IDNETIFICAREMOS DICHO CACHE
    //"unless" SON LAS CONDICIONES DE GUARDADO
    //@Cacheable(value = "usuario", key = "#idUsuario", unless = "#result == null ")
    public UsuarioModel listarUsuaiorId(Long idUsuario){
        //log.warn(idUsuario);
        //return convertidorEntidades.convertirUsuario(usuarioRepository.findById(idUsuario).orElse(null));
        return gson.fromJson(redisTemplate.opsForValue().get(idUsuario.toString()), UsuarioModel.class);
    }

    //"key" ESPECIFICA QUE USE EL ID DE UNO DE LOS PARAMETROS
    //@CachePut(cacheNames = CacheConfig.USER_CACHE, key = "#id", unless = "#result == null")
   //@CachePut(value = "usuario", key = "#usuarioEntity.idUsuario") //PARA ACTUALIZAR LA CACHE
    public UsuarioModel actualizarUsuario(UsuarioEntity usuarioEntity){
        redisTemplate.opsForValue().set(usuarioEntity.getIdUsuario().toString(), gson.toJson(usuarioEntity));
        return convertidorEntidades.convertirUsuario(usuarioRepository.save(usuarioEntity));
    }

    //@CacheEvict(cacheNames = CacheConfig.USER_CACHE, key = "#id")
    //@CacheEvict(value = "usuario", allEntries = true) //PARA ELIMINAR DATOS DE LA CACHE
    public void eliminarUsuario(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        usuarioRepository.delete(usuario);
        redisTemplate.delete(id.toString());
    }

}
