package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.UserReviewDAO

@Service
class UserReviewService (private val userDAO: UserReviewDAO) {

      fun getByIdPlatform(platform: Platform, platformID: String, nicknames: String): UserReview {
        var found = this.userDAO.getFirstByPlatformIDAndPlatform(platformID, platform)
        if (found.isEmpty){
            throw Exception("User Not found")
        }
        return found.get()
    }

}