package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carro")
public class CarroEntity implements Serializable {

    public CarroEntity(Long idCarro) {
        this.idCarro = idCarro;
    }

    public CarroEntity(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    @Id
    @Column(name = "idcarro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    @Column(name = "marcacarro")
    private String marcaCarro;

    @OneToMany(mappedBy = "carroFk")
    @JsonManagedReference
    private List<UsuarioEntity> usuarios;

}
