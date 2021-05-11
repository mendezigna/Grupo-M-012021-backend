package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ReviewDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.UserReviewDAO
import java.util.*

@Service
class ReviewService(private val reviewDAO: ReviewDAO,
                    private val titleDAO: TitleDAO,
                    private val userReviewDAO: UserReviewDAO
                    ) {

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

    fun addLiking(id: Long, isLike: Boolean): Review {
        var review: Review =  reviewDAO.findById(id).get()
        review.addLike(isLike)
        return this.reviewDAO.save(review)
    }

    fun save(review: Review, title: Title, user: UserReview): Review {
        review.user = user
        review.setTitleReview(title)
        return this.reviewDAO.save(review)
    }

    fun create(review: Review, titleId: String): Review {
        var title: Title = titleDAO.findByTitleId(titleId)
        var userFound: Optional<UserReview> =
                this.userReviewDAO.getFirstByPlatformIDAndPlatform(review.user?.platformID!!, review.user!!.platform!!)
        var user: UserReview = if (userFound.isEmpty) {
            this.userReviewDAO.save(review.user!!)
        } else {
            userFound.get()
        }
        return this.save(review, title, user)
    }
}