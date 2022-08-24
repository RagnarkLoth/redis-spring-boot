package com.example.demo.models;

import com.example.demo.entitys.CarroEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CarroModel implements Serializable {

    public CarroModel(CarroEntity carroEntity){

        this.idCarro = carroEntity.getIdCarro();
        this.marcaCarro=carroEntity.getMarcaCarro();

    }

    private Long idCarro;

    private String marcaCarro;

}
