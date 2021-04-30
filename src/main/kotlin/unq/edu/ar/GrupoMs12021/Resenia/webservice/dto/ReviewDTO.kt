package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.review.*
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*

data class ReviewDTO(val title: TitleDTO, val overview: String?, val description: String?, val rating: Int?, val premium: Boolean?,
                     val user: UserReviewDTO?, val userNickname: String?, val spoiler: Boolean?, val date: Date?, val liking: LikingDTO?,
                     val reports: List<ReportDTO>?) {

    companion object {
        fun fromModel(rev: Review): ReviewDTO {
            return ReviewDTO(titleLightwight(rev.title!!), rev.overview, rev.description, rev.rating, rev.premium,
                    UserReviewDTO.fromModel(rev.user!!), rev.userNickname, rev.spoiler, rev.date, LikingDTO.fromModel(rev.liking!!),
                    ReportDTO.fromModel(rev.reports!!)
            )
        }

        fun fromModel(revs: List<Review>): List<ReviewDTO> {
            return revs.map { fromModel(it) }
        }

        fun titleLightwight(title: Title):TitleDTO{
            var dto = TitleDTO(title.titleId!!, title.titleType!!, title.name!!, title.runtimeMinutes!!, title.startYear!!, title.endYear, title.getGenres(), null)
            var a = 0
            return dto
        }
    }
}

data class UserReviewDTO(val platform: Platform?, val platformID: String?, val nicknames: String?, val location: String?, val language: String?) {
    companion object {
        fun fromModel(user: UserReview): UserReviewDTO {
            return UserReviewDTO(user.platform, user.platformID, user.nicknames, user.location, user.language)
        }

        fun fromModel(users: List<UserReview>): List<UserReviewDTO> {
            return users.map { fromModel(it) }
        }
    }
}

data class LikingDTO(val likes: Int?, val dislikes: Int?) { //, val users: List<UserReviewDTO>?
    companion object {
        fun fromModel(liking: Liking): LikingDTO {
            return LikingDTO(liking.likes, liking.dislikes)//, UserReviewDTO.fromModel(liking.users!!))
        }
    }
}

data class ReportDTO(val reason: String, val user: UserReviewDTO) {
    companion object {
        fun fromModel(report: Report): ReportDTO {
            return ReportDTO(report.reason!!, UserReviewDTO.fromModel(report.user!!))
        }

        fun fromModel(reports: List<Report>): List<ReportDTO> {
            return reports.map { fromModel(it) }
        }
    }
}

