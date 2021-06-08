package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import com.fasterxml.jackson.annotation.JsonInclude
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import java.util.*
import javax.persistence.ManyToOne

@JsonInclude(JsonInclude.Include.NON_NULL)
class ReviewGenericDTO {

    var ReviewType: String? = null

    // common fields
    var id: Long? = null
    var titleID: String? = null
    var overview: String? = null
    var description: String? = null
    var rating: Float? = null
    var date: Date? = null
    var likes: Int? = 0
    var dislikes: Int? = 0
    var platform: Platform? = null
    var platformID: String? = null
    var language: String? = null

    // PublicReview fields
    var nicknames: String? = null
    var location: String? = null
    var spoiler: Boolean? = null
    var reportsId: List<ReportDTO>? = listOf()

}

class ReportDTO {
    var id: Long ? = null
    var reason: String? = null
}
