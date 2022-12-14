package com.example.demo.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

//
@Configuration
@Log4j2
public class CacheConfig extends CachingConfigurerSupport {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    public static final String USER_CACHE = "key-solicitud";

    @Bean
    public CacheManager cacheManager() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        //CONFIGURACION DE REDISCACHEMANAGER
        RedisSerializationContext.SerializationPair jsonSerializer = RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer);

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory).cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(1))
                                .serializeValuesWith(jsonSerializer)
                                .disableCachingNullValues()
                ).build();
    }

    //FORMA CON JEDIS
    //CONEXION PERSONALIZADA(Si no se declaran los valores como servidor o puerto, se colocan los predeterminados)
    /*
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
       // return new JedisConnectionFactory(); FORMA PREDETERMINADA
        //FORMA PERSONALIZADA
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

    //PARA CONSULTAR DATOS DE UN REPOSITORIO PERSONALIZADO
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
    */

    //FORMA DOS
/*
    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private  int REDIS_PORT;

    @Bean
    public RedisTemplate<String, String> redisTemplate(){

        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));

        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

        //ZONA DE JEDIS
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;

    }
*/

}
