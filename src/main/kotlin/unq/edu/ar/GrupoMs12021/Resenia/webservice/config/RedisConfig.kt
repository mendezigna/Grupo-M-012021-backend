package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import redis.clients.jedis.Jedis
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite


@Component
class RedisConfig() {

    var jedisConnectionFactory: JedisConnectionFactory? = null

    init {
        this.jedisConnectionFactory = getNewJedisConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, TitleLite>? {
        // Cliente redis tipo HK para cache
        val redisTemplate: RedisTemplate<String, TitleLite> = RedisTemplate<String, TitleLite>()
        redisTemplate.setConnectionFactory(this.jedisConnectionFactory!!)
        return redisTemplate
    }

    fun getNewJedisConnectionFactory(): JedisConnectionFactory {
        val host: String = "localhost"
        val port: Int = 6379
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(host, port)
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    fun getJedisClientPublisher(): Jedis {
        return Jedis()
    }

}