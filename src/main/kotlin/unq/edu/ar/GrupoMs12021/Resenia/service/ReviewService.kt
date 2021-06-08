package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.impl.ReviewFilterService
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.ReviewFilter
import java.lang.RuntimeException
import java.util.*

@Service
class ReviewService(private val reviewDAO: ReviewDAO,
                    private val titleDAO: TitleDAO,
                    private val filterSearch: ReviewFilterService
                    ) {

    fun getAll(filter: ReviewFilter): List<Review> {
        return this.filterSearch.findReviewsFiltered(filter)
    }

    fun getById(id: Long): Review {
        val found: Optional<Review> = this.reviewDAO.findById(id)
        if (found.isEmpty) {
            throw Exception(String.format("Review not found id:[%s]", id))
        }
        return found.get()
    }

    fun getByTitleId(titleId: String): List<Review> {
        var filter: ReviewFilter = ReviewFilter.createEmptyFilter()
        filter.titleId = titleId
//        return this.reviewDAO.findReviewsByTitleTitleId(titleId)
        return this.filterSearch.findReviewsFiltered(filter)
    }

    fun addLiking(id: Long, isLike: Boolean): Review {
        var review: Review =  reviewDAO.findById(id).get()
        review.addLike(isLike)
        return this.reviewDAO.save(review)
    }

    fun save(review: Review): Review {
        return this.reviewDAO.save(review)
    }

    fun create(review: Review, titleId: String): Review {
        var title: Title = titleDAO.findByTitleId(titleId)
        // check duplic reviews
        review.setTitleReview(title)
        return this.save(review)
    }

    fun getReports(id: Long): List<Report>? {
        val found: Optional<Review> = this.reviewDAO.findById(id)
        if (found.isPresent && found.get() is PublicReview ){
            return (found.get() as PublicReview).reports
        }else{
            throw RuntimeException(String.format("PublicReview not found id:[%s]", id))
        }
    }

    fun createReport(idReview: Long, report: Report): PublicReview {
        val found: Optional<Review> = this.reviewDAO.findById(idReview)
        if (found.isPresent && found.get() is PublicReview ){
            val review = found.get()
            (review as PublicReview).addReport(report.reason!!)
            return this.reviewDAO.save(review)
        }else{
            throw RuntimeException(String.format("PublicReview not found id:[%s]", idReview))
        }
    }
}