package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO
import java.util.*

@Service
class ReviewService(private val dao: ReviewDAO) {

    fun getAll(): List<Review> {
        return dao.findAll().toList()
    }

    fun getById(id: String): Review {
        return this.dao.findById(id.toLong()).get()
    }

    fun getByTitleId(titleId: String): List<Review> {
        var res = this.dao.findReviewsByTitleTitleId(titleId)
        var a = res.size
        return res
    }
}