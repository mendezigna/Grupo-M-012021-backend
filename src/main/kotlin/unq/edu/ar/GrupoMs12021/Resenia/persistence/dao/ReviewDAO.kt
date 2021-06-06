package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review

@Repository
interface ReviewDAO : JpaRepository<Review, Long> {

    fun findReviewsByTitleTitleId (titleId: String): List<Review>;
}