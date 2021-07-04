package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class TitleLiteDAO {

    @PersistenceContext
    private val em: EntityManager? = null

    fun getAllTitlesLite(): List<TitleLite> {
        var typedQuery = em!!.createNativeQuery(getQueryAll())
        var results: List<Array<Any>> = typedQuery.resultList as List<Array<Any>>
        var titles = mutableListOf<TitleLite>()
        results.forEach{
            it -> titles.add(this.mapToTitleLite(it))
        }

        return titles.toList()
    }

    fun getLatestHourTitles(): List<TitleLite> {
        var sqlquery: String = getQueryLatestHour()

        // execute query
        var typedQuery = em!!.createNativeQuery(sqlquery)
        var results: List<Array<Any>> = typedQuery.resultList as List<Array<Any>>

        var titles = mutableListOf<TitleLite>()
        results.forEach{
            it -> titles.add(this.mapToTitleLite(it))
        }
        return titles.toList()
    }

    private fun mapToTitleLite(result: Array<Any>): TitleLite {
        // maps Object[3] to init TitleLite
        return TitleLite(
                result[0] as String,
                (result[1] as BigInteger).intValueExact(),
                (result[2] as Double).toFloat()
        )
    }

    private fun getQueryAll() =
            "SELECT title.title_id, count(review.id), avg(review.rating) " +
                    "FROM review JOIN title ON review.title_id = title.id GROUP BY title.title_id"


    private fun getQueryLatestHour(): String {
        val formatter = SimpleDateFormat("dd MM yyyy HH")
        val lastHour = Date(System.currentTimeMillis()-60*60*1000)

        return "SELECT title.title_id, count(review.id), avg(review.rating) " +
                "FROM review JOIN title ON review.title_id = title.id " +
                String.format(" WHERE review.date > TO_TIMESTAMP('%s:00:00', 'DD mm YYYY HH:mm:SS') ",formatter.format(lastHour)) +
                "GROUP BY title.title_id"
    }

}