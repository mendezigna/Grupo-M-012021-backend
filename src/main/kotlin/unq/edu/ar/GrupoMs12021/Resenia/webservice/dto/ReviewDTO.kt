package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.review.*
import java.util.*

data class ReviewDTO(val id: Long, val titleID: String, val overview: String?, val description: String?, val rating: Int?, val premium: Boolean?,
                     val user: UserReviewDTO?, val userNickname: String?, val spoiler: Boolean?, val date: Date?, val liking: LikingDTO?,
                     val reports: List<ReportDTO>?) {

    companion object {
        fun fromModel(rev: Review): ReviewDTO {
            return ReviewDTO(rev.id!!, rev.title!!.titleId!!, rev.overview, rev.description, rev.rating, rev.premium,
                    UserReviewDTO.fromModel(rev.user!!), rev.userNickname, rev.spoiler, rev.date, LikingDTO.fromModel(rev.liking!!),
                    ReportDTO.fromModel(rev.reports!!)
            )
        }

        fun fromModel(revs: List<Review>): List<ReviewDTO> {
            return revs.map { fromModel(it) }
        }

        fun fromService(dto: ReviewDTO): Review {
            var entity = Review(null, dto.overview!!, dto.description!!, dto.rating!!, dto.premium, UserReviewDTO.fromService(dto.user!!),
                    dto.userNickname!!, dto.spoiler, dto.date)
            dto.id ?: run {
                System.out.println("so, isnt null")
                System.out.println(dto.id)
                entity.id = dto.id
            }
            return entity
        }
    }
}

data class UserReviewDTO(val platform: Platform?, val platformID: String?, val nicknames: String?, val location: String?, val language: String?) {
    companion object {
        fun fromModel(user: UserReview): UserReviewDTO {
            return UserReviewDTO(user.platform, user.platformID, user.nicknames!!, user.location!!, user.language!!)
        }

        fun fromService(userdto: UserReviewDTO): UserReview {
            var entity = UserReview(userdto.platform!!, userdto.platformID!!, userdto.nicknames!!)
            return entity
        }
    }
}

data class LikingDTO(val likes: Int?, val dislikes: Int?) {
    companion object {
        fun fromModel(liking: Liking): LikingDTO {
            return LikingDTO(liking.likes, liking.dislikes)
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
