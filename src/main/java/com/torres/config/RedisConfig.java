package com.torres.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

  @Value("${redis.host}")
  private String host;

  @Value("${redis.port}")
  private int port;

  @Value("${redis.password}")
  private String password;

  @Value("${redis.database}")
  private int database;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(host);
    configuration.setPort(port);
    configuration.setPassword(RedisPassword.of(password));
    configuration.setDatabase(database);
    logger.info("redis host={} port={} database={}", host, port, database);
    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    final RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());
    template.setDefaultSerializer(new StringRedisSerializer());
    return template;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(jedisConnectionFactory());
    template.afterPropertiesSet();
    return template;
  }
}
