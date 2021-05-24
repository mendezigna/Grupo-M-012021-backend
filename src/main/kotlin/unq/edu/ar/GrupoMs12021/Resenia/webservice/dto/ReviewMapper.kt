package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PremiumReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review


@Component
class ReviewMapper {

    var mapper: ModelMapper? = ModelMapper()
    val Type_Public = "PublicReview"
    val Type_Premium = "PremiumReview"

    constructor() {
        this.mapper = ModelMapper()
        this.mapper!!.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    fun toDto(rev: Review): ReviewGenericDTO {
        var dto: ReviewGenericDTO? = null
        if (rev is PremiumReview){
            dto = mapper!!.map(rev, ReviewGenericDTO::class.java)
            dto.ReviewType = Type_Premium
        }
        else if (rev is PublicReview) {
            dto = mapper!!.map(rev, ReviewGenericDTO::class.java)
            dto.reportsId = rev.reports!!.map { report:Report -> toDto(report) }
            dto.ReviewType = Type_Public
        }
        dto!!.titleID = rev.title!!.titleId
        return dto
    }

    fun toEntity(dto: ReviewGenericDTO): Review {
        if (dto.ReviewType.equals(Type_Premium)){
            return this.mapper!!.map(dto, PremiumReview::class.java)
        }
        else if(dto.ReviewType.equals(Type_Public)) {
            return this.mapper!!.map(dto, PublicReview::class.java)
        }
        return Review()
    }

    fun toDto(reviewReport: Report): ReportDTO {
        return this.mapper!!.map(reviewReport, ReportDTO::class.java)
    }
    fun toEntity(dto: ReportDTO): Report {
        return this.mapper!!.map(dto, Report::class.java)
    }

}
