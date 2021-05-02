package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.review.*
import java.util.*

data class ReviewDTO(val id: Long, val titleID: String, val overview: String?, val description: String?, val rating: Int?, val premium: Boolean?,
                     val user: UserReviewDTO?, val spoiler: Boolean?, val date: Date?, val reports: List<ReportDTO>? = listOf(), val likes: Int?, val dislikes: Int?) {

    companion object {
        fun fromModel(rev: Review): ReviewDTO {
            return ReviewDTO(rev.id!!, rev.title!!.titleId!!, rev.overview, rev.description, rev.rating, rev.premium,
                    UserReviewDTO.fromModel(rev.user!!), rev.spoiler, rev.date, ReportDTO.fromModel(rev.reports!!), rev.likes, rev.dislikes
            )
        }

        fun fromModel(revs: List<Review>): List<ReviewDTO> {
            return revs.map { fromModel(it) }
        }

        fun fromService(dto: ReviewDTO): Review {
            var entity = Review(null, dto.overview!!, dto.description!!, dto.rating!!, dto.premium, UserReviewDTO.fromService(dto.user!!), dto.spoiler, dto.date)
            dto.id ?: run {
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
            return UserReview(userdto.platform!!, userdto.platformID!!, userdto.nicknames!!, userdto.location, userdto.language)
        }
    }
}

data class ReportDTO(val reason: String) {
    companion object {
        fun fromModel(report: Report): ReportDTO {
            return ReportDTO(report.reason!!)
        }

        fun fromModel(reports: List<Report>): List<ReportDTO> {
            return reports.map { fromModel(it) }
        }
    }
}
