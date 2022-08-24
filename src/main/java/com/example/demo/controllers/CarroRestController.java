package com.example.demo.controllers;

import com.example.demo.entitys.CarroEntity;
import com.example.demo.entitys.UsuarioEntity;
import com.example.demo.models.CarroModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.CarroService;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
public class CarroRestController {

    @Autowired
    private CarroService carroService;


    @PostMapping("/crear")
    public ResponseEntity<CarroModel> crearCarro(@RequestBody CarroEntity carroEntity){

        return ResponseEntity.ok(carroService.crearCarro(carroEntity));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<CarroModel> actualizarCarro(@RequestBody CarroEntity carroEntity){

        return ResponseEntity.ok(carroService.actualizarCarro(carroEntity));

    }

    @GetMapping("/listar")
    public ResponseEntity<List<CarroModel>> listarCarros(){

        return ResponseEntity.ok(carroService.listarCarros());

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<CarroModel> listarCarroId(@PathVariable Long id){
        return ResponseEntity.ok(carroService.listarCarroID(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCarro(@PathVariable Long id){
        carroService.eliminarUsuario(id);
        return ResponseEntity.ok("Se elimno correctamente");

    }

}
