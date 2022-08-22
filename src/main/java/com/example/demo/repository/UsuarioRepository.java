package com.example.demo.repository;

import com.example.demo.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


}
