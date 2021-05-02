package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.UserReviewDAO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.UserReviewDTO
import java.util.*

@Service
class UserReviewService (private val userDAO: UserReviewDAO) {

      fun getByIdPlatform(platform: Platform, platformID: String, nicknames: String): Optional<UserReview> {
        return this.userDAO.getFirstByPlatformIDAndPlatform(platformID, platform)
    }

    fun save(user: UserReview): UserReview {
        return this.userDAO.save(user)
    }

}