package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import java.util.*

@Repository
interface UserReviewDAO: CrudRepository<UserReview, Long> {

    fun getFirstByPlatformIDAndPlatform(platformID: String, platform: Platform): Optional<UserReview>
}