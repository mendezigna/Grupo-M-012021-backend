package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleLiteDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleLiteRedisDAO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite

@Service
class CacheService(
        @Autowired private var repositoryCache: TitleLiteRedisDAO,
        @Autowired private val repositoryTitleLite: TitleLiteDAO
        ) {

    fun loadSaveAll(){
        var results: List<TitleLite> = repositoryTitleLite.getAllTitlesLite()
        results.forEach{
//            it -> this.repositoryCache.save(this.mapToTitleLite(it))
        }
    }

    fun getByTitle(titleId: String): TitleLite? {
        return this.repositoryCache.findById(titleId)
    }

    fun updateLatestHour(){
        var results: List<TitleLite> = repositoryTitleLite.getLatestHourTitles()
        results.forEach{
//            it -> this.repositoryCache.update(this.mapToTitleLite(it))
        }
    }

    fun deleteByTitle(titleId: String) {
        this.repositoryCache.delete(titleId)
    }

}
