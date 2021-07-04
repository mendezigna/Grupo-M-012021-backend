package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite


@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(): RedisTemplate<String, TitleLite>? {
        val redisTemplate: RedisTemplate<String, TitleLite> = RedisTemplate<String, TitleLite>()
        redisTemplate.setConnectionFactory(getJedisConnectionFactory())
        return redisTemplate
    }

    @Bean
    fun getJedisConnectionFactory(): JedisConnectionFactory {
        // default port 6379
        val redisStandaloneConfiguration = RedisStandaloneConfiguration("localhost", 6379)
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

}