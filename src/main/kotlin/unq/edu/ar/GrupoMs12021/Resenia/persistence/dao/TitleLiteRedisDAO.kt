package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite


@Repository
class TitleLiteRedisDAO {

    private var redisTemplate: RedisTemplate<String, TitleLite>? = null
    //HashOperations: access Redis cache
    private var hashOperations: HashOperations<String, String, TitleLite>? = null

    constructor(@Autowired redisTemplate: RedisTemplate<String, TitleLite>?) {
        this.redisTemplate = redisTemplate
        hashOperations = this.redisTemplate!!.opsForHash()
    }

    fun findAll(): Map<String, TitleLite> {
        return hashOperations!!.entries("TITLE")
    }

    fun save(title: TitleLite){
        hashOperations!!.put("TITLE", title.titleId!!, title)
    }

    fun update(title: TitleLite){
        save(title)
    }

    fun findById(titleId: String): TitleLite? {
        return hashOperations!!.get("TITLE", titleId)
    }
    fun delete(titleId: String){
        hashOperations!!.delete("TITLE", titleId)
    }
}