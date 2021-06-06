package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.filter

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Actor
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.TitleFilter
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.*


@Service
@Transactional(readOnly = true)
class SearchTitlesService {

    @PersistenceContext
    private val em: EntityManager? = null

    fun findTitlesFiltered(filter: TitleFilter): List<Title> {

        val cb = em!!.criteriaBuilder

        val countquery: CriteriaQuery<Long> = cb.createQuery(Long::class.java)
        val counttitleroot: Root<Title> = countquery.from(Title::class.java)
        countquery.select(cb.countDistinct(counttitleroot)).distinct(true)

        val titlequery: CriteriaQuery<Title> = cb.createQuery(Title::class.java).distinct(true)
        val titleroot: Root<Title> = titlequery.from(Title::class.java)

        filter.buildFilterMap().forEach { (k, v) ->
            run {
                // filtros Titles
                titlequery.where(this.createPredicate(cb, titleroot, k, v)!!)
                // filtros Count
                countquery.where(this.createPredicate(cb, counttitleroot, k, v)!!)
            }
        }

        // execute total rows count
        val resCount = em.createQuery(countquery).singleResult
        val totalPages: Int = this.roundUp(resCount.toInt(), filter.sizePage)

        if (filter.numPage>totalPages){
            return listOf()
        }

        // titles query
        val indexedQuery = em.createQuery(titlequery)
                .setFirstResult(filter.numPage * filter.sizePage)
                .setMaxResults(filter.sizePage)

        // execute queryTitles per page
        return indexedQuery.resultList
    }


    // Returns condition to fill Criteria
    private fun createPredicate(criteriabuilder: CriteriaBuilder, rootTitle: Root<Title>,
                                filter: String, filterValue: Any): Predicate? {
        when (filter) {
            "titleType" -> return criteriabuilder.equal(rootTitle.get<TitleType>("titleType"), filterValue)
            "decade" -> return criteriabuilder.between(rootTitle.get("startYear"), filterValue as Int, filterValue + 10)
            "genre" -> return criteriabuilder.like(rootTitle.get("genres"), "%${(filterValue as Genres).toString()}%")
            "titlename" -> return criteriabuilder.like(rootTitle.get("name"), "%${filterValue as String}%")
            "rating" -> {
                val joinReview: Join<Title, Review> = rootTitle.join("reviews", JoinType.LEFT)
                return criteriabuilder.greaterThanOrEqualTo(joinReview.get("rating"), filterValue as Float)
            }
            "likesGT" -> {
                val joinReview: Join<Title, Review> = rootTitle.join("reviews", JoinType.LEFT)
                return criteriabuilder.greaterThan(joinReview.get("likes"), filterValue as Int)
            }
            "actor" -> {
                val joinCast: Join<Title, Cast> = rootTitle.join("cast", JoinType.LEFT)
                val joinActor: Join<Cast, Actor> = joinCast.join("actors", JoinType.LEFT)
                return criteriabuilder.like(joinActor.get("name"), "%${filterValue}%")
            }
            "director" -> {
                val joinCast: Join<Title, Cast> = rootTitle.join("cast", JoinType.LEFT)
                return criteriabuilder.like(joinCast.get("director"), "%${filterValue}%")
            }
            else -> return null
        }
    }


    private fun roundUp(num: Int, divisor: Int): Int {
        return (num + divisor - 1) / divisor
    }

}


