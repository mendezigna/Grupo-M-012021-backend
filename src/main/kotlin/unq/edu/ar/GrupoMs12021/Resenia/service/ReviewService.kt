package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO

@Service
class ReviewService(private val dao: ReviewDAO) {

    fun getAll(): List<Review> {
        return dao.findAll().toList()
    }
}