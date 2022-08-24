package com.example.demo.controllers;

import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Log4j2
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/crear")
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody UsuarioEntity usuarioEntity){

        log.warn(usuarioEntity.getCarroFk().getIdCarro());
        return ResponseEntity.ok(usuarioService.crearUsuario(usuarioEntity));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioModel> actualizarUsuario(@RequestBody UsuarioEntity usuarioEntity){

        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioEntity));

    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){

        return ResponseEntity.ok(usuarioService.listarUsuarios());

    }

    @GetMapping("/listar/{nombre}")
    public ResponseEntity<UsuarioModel> listarUsuarioId(@PathVariable String nombre){
        return ResponseEntity.ok(usuarioService.listarUsuaiorId(nombre));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Se elimno correctamente");

    }

}
