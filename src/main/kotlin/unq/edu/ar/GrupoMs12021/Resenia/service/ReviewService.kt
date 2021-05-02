package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO
import java.util.*

@Service
class ReviewService(private val reviewDAO: ReviewDAO) {

    fun getAll(): List<Review> {
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

    fun addLiking(review: Review, isLike: Boolean): Review {
        review.addLike(isLike)
        return this.reviewDAO.save(review)
    }

    fun save(review: Review, title: Title, user: UserReview): Review {
        review.user = user
        review.setTitleReview(title)
        return this.reviewDAO.save(review)
    }
}