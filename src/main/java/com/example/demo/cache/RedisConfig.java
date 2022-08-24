package com.example.demo.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
public class RedisConfig {

    //FABRICA DE CONEXIONES DE REDIS PARA CONECTAR AL SERVIDOR DEL REDIS
  /*
     @Bean
    public LettuceConnectionFactory redisConnectionFactory(){

         return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));

     }
 */
}
