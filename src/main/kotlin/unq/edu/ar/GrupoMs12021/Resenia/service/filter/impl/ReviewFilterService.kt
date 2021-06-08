package unq.edu.ar.GrupoMs12021.Resenia.service.filter.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.FiltersReviewFields
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.ReviewFilter
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.*


@Service
@Transactional(readOnly = true)
class ReviewFilterService {

    @PersistenceContext
    private val em: EntityManager? = null

    fun findReviewsFiltered(filter: ReviewFilter):List<Review> {

        var cb = em!!.criteriaBuilder

        var countquery: CriteriaQuery<Long> = cb.createQuery(Long::class.java)
        val countreviewroot: Root<Review> = countquery.from(Review::class.java)
        countquery.select(cb.countDistinct(countreviewroot))
                .distinct(true)

        var reviewquery: CriteriaQuery<Review> = cb.createQuery(Review::class.java)
        val reviewroot: Root<Review> = reviewquery.from(Review::class.java)

        // conditions/filters
        reviewquery.where(*buildPredicatesArray(cb, reviewroot, reviewquery as CriteriaQuery<Any>, filter));
        countquery.where(*buildPredicatesArray(cb, countreviewroot, countquery as CriteriaQuery<Any>, filter));

        // order to show list
        reviewquery.orderBy(addFiltersOrder(cb, reviewroot, filter))


        // execute total rows count
        val resCount = em.createQuery(countquery as CriteriaQuery<Long>).singleResult
        val totalPages: Int = roundUp(resCount.toInt(), filter.sizePage)

        if (filter.numPage>totalPages){
            return listOf()
        }

        // titles query
        val indexedQuery = em.createQuery(reviewquery as CriteriaQuery<Review>)
                .setFirstResult(filter.numPage * filter.sizePage)
                .setMaxResults(filter.sizePage)

        // execute queryTitles per page
        return indexedQuery.resultList

    }

    private fun buildPredicatesArray(cb: CriteriaBuilder, reviewroot: Root<Review>, query: CriteriaQuery<Any>, filter: ReviewFilter): Array<Predicate> {
        var predicates: MutableList<Predicate> = mutableListOf(
                // Default conditions:
                createReportsFilter(cb,reviewroot,query as CriteriaQuery<Any>)!!
        )
        filter.buildFilterMap().forEach { (k, v) ->
            predicates.add(this.createPredicate(cb, reviewroot, k, v)!!)
        }
        return predicates.toTypedArray()
    }

    private fun createPredicate(criteriaBuilder: CriteriaBuilder, root: Root<Review>, filter: String, filterValue: Any): Predicate? {
        when (filter) {
            FiltersReviewFields.titleID -> {
                val join: Join<Review, Title> = root.join("title", JoinType.LEFT)
                return criteriaBuilder.like(join.get<String>("titleId"), "%${filterValue}%")
            }
            FiltersReviewFields.platform -> return criteriaBuilder.equal(root.get<Platform>("platform"), filterValue)
            FiltersReviewFields.language -> return criteriaBuilder.like(root.get("language"), "%${filterValue}%")
            FiltersReviewFields.location -> {
                return criteriaBuilder.like(criteriaBuilder.treat(root, PublicReview::class.java).get("location"), "%${filterValue}%")
            }
            FiltersReviewFields.reviewType -> return criteriaBuilder.equal(root.type(), filterValue)
            FiltersReviewFields.spoiler -> {
                return criteriaBuilder.equal(criteriaBuilder.treat(root, PublicReview::class.java).get<Boolean>("spoiler"), filterValue)
            }
            FiltersReviewFields.rating -> return criteriaBuilder.greaterThanOrEqualTo(root.get<Float>("rating"), filterValue as Float)
            else -> return null
        }
    }

    private fun addFiltersOrder(criteriaBuilder: CriteriaBuilder?, root: Root<Review>, filter: ReviewFilter): MutableList<Order> {
        var list: MutableList<Order> = mutableListOf()
        if (filter.order!=null && filter.sort!= null){
            list.add(createOrder(criteriaBuilder!!, root, filter.order!!, filter.sort!!)!!)
        }
        // Order by default
        list.add(createOrder(criteriaBuilder!!, root, "desc", "likes")!!)
        return list
    }

    private fun createOrder(criteriaBuilder: CriteriaBuilder, root: Root<Review>, order: String, sort: String): Order? {
        var orderBy: Order? = null
        if(order.contains("desc")){
            when (sort){
                "date" -> orderBy = criteriaBuilder.desc(root.get<Date>("date"))
                "rating" -> orderBy =  criteriaBuilder.desc(root.get<Date>("rating"))
                "likes" -> orderBy =  criteriaBuilder.desc(root.get<Int>("likes"))
            }
        }else{
            when (sort){
                "date" -> orderBy =  criteriaBuilder.asc(root.get<Date>("date"))
                "rating" -> orderBy = criteriaBuilder.asc(root.get<Date>("rating"))
            }
        }
        return orderBy
    }


    private fun createReportsFilter(cb: CriteriaBuilder, root: Root<Review>, query: CriteriaQuery<Any>): Predicate? {
        // Restricts any reported Review
        var subqueryRep: Subquery<Report> = query.subquery(Report::class.java)
        val subquerRoot = subqueryRep.from(Report::class.java)
        subqueryRep.distinct(true)
                .select(subquerRoot.get("review"))
                .where(cb.equal(subquerRoot.get<Long>("review"), root.get<Long>("id")))
        return cb.not(cb.exists(subqueryRep))
    }

    private fun roundUp(num: Int, divisor: Int): Int {
        return (num + divisor - 1) / divisor
    }

}