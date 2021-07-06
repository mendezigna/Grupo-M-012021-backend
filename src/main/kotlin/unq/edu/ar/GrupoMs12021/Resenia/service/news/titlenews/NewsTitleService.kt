package unq.edu.ar.GrupoMs12021.Resenia.service.news.titlenews

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis

@Service
class NewsTitleService(@Qualifier("getJedisClientPublisher") @Autowired val clientPublisher: Jedis) {

    val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(NewsTitleService::class.java)
    private val topicTitleNews = "TitleNews"

    fun sendtoTitleNews(titleId: String){
        try{
            this.clientPublisher.sadd(topicTitleNews, titleId)
            LOGGER.info("Published News to "+topicTitleNews)
        } catch (e: Exception){
          e.printStackTrace()
          LOGGER.error("Failed to publish on TitleNews -"+ titleId,e )
        }
    }

    fun getLastfromTitleNews(): String? {
        try{
            return this.clientPublisher.spop(topicTitleNews)
        } catch (e: Exception){
            e.printStackTrace()
            LOGGER.error("Failed to retrieve item from TitleNews",e )
            throw RuntimeException(e)
        }
    }

}