package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import java.lang.RuntimeException
import java.util.*

@Service
class ReviewService(private val reviewDAO: ReviewDAO,
                    private val titleDAO: TitleDAO
                    ) {

    fun getAll(paramsMap: MutableMap<String, Array<String>>): List<Review> {
//        Se debe poder filtrar por
//        plataforma, spoiler alert, tipo (review o crítica), idioma y país.
//        -paginados
//        -ordenar por rating y/o fecha,

//        val res = this.search.findTitlesFiltered()
//        val res = this.search.findTitlesFiltered()
        return reviewDAO.findAll().toList()
    }

    fun getById(id: Long): Review {
        val found: Optional<Review> = this.reviewDAO.findById(id)
        if (found.isEmpty) {
            throw Exception(String.format("Review not found id:[%s]", id))
        }
        return found.get()
    }

    fun getByTitleId(titleId: String): List<Review> {
        return this.reviewDAO.findReviewsByTitleTitleId(titleId)
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
            var report = (review as PublicReview).addReport(report.reason!!)
            return this.reviewDAO.save(review)
        }else{
            throw RuntimeException(String.format("PublicReview not found id:[%s]", idReview))
        }
    }
}