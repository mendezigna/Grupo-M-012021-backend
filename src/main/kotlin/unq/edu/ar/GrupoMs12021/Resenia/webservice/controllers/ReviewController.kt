package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService

import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReportDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewGenericDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewMapper
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.ApiKey

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/reviews")
class ReviewController(private val reviewService: ReviewService) {

    @Autowired
    var mapper: ReviewMapper? = ReviewMapper()

    @ApiKey
    @GetMapping()
    fun getAll(): List<ReviewGenericDTO> {
        return  this.reviewService.getAll().map { rev: Review -> mapper!!.toDto(rev) }
    }

    @ApiKey
    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.getById(id))
    }

    @ApiKey
    @PostMapping("/{id}/like")
    fun likeReview(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, true))
    }

    @ApiKey
    @PostMapping("/{id}/dislike")
    fun dislikeReview(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, false))
    }

    @ApiKey
    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewGenericDTO> {
        return this.reviewService.getByTitleId(titleId).map { rev: Review -> mapper!!.toDto(rev) }
    }

    @ApiKey
    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewGenericDTO): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.create(mapper!!.toEntity(reviewDTO), titleId))
    }

    @GetMapping("/{id}/reports")
    fun getReportsByReview(@PathVariable id: Long): List<ReportDTO> {
        return this.reviewService.getReports(id)!!.map { report: Report -> mapper!!.toDto(report)  }
    }

    @PostMapping("/{id}/reports")
    fun createReportReview(@PathVariable id: Long, @RequestBody reportDTO: ReportDTO): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.createReport(id, mapper!!.toEntity(reportDTO)))
    }

}
