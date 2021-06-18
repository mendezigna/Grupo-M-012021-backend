package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Report
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.ReviewFilter

import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReportDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewGenericDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewMapper
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.ApiKey
import java.util.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService) {

    @Autowired
    var mapper: ReviewMapper? = ReviewMapper()

    @ApiKey
    @GetMapping()
    fun getAll(@RequestParam(required = false) numPage:Int?,
               @RequestParam(required = false) sizePage:Int?,
               @RequestParam(required = false) orderBy:String?,
               @RequestParam(required = false) rating:Float?,
               @RequestParam(required = false) platform: Platform?,
               @RequestParam(required = false) location: String?,
               @RequestParam(required = false) language: String?,
               @RequestParam(required = false) spoiler: Boolean?,
               @RequestParam(required = false) reviewType: String?,
               @RequestParam(required = false) date: Date?
    ): List<ReviewGenericDTO> {
        var filter = ReviewFilter(numPage, sizePage,orderBy,platform,rating, spoiler, reviewType, language, location, date)
        return  this.reviewService.getAll(filter).map { rev: Review -> mapper!!.toDto(rev) }
    }

    @ApiKey
    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.getById(id))
    }

    @ApiKey
    @PostMapping("/{id}/like")
    fun likeReview(@PathVariable id: Long, @RequestHeader("ApiKey") apiKey: String): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, true, apiKey))
    }

    @ApiKey
    @PostMapping("/{id}/dislike")
    fun dislikeReview(@PathVariable id: Long, @RequestHeader("ApiKey") apikey: String): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, false, apikey))
    }

    @ApiKey
    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewGenericDTO> {
        return this.reviewService.getByTitleId(titleId).map { rev: Review -> mapper!!.toDto(rev) }
    }

    @ApiKey
    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewGenericDTO, @RequestHeader("ApiKey") apikey: String): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.create(mapper!!.toEntity(reviewDTO), titleId, apikey))
    }

    @ApiKey
    @GetMapping("/{id}/reports")
    fun getReportsByReview(@PathVariable id: Long): List<ReportDTO> {
        return this.reviewService.getReports(id)!!.map { report: Report -> mapper!!.toDto(report)  }
    }

    @ApiKey
    @PostMapping("/{id}/reports")
    fun createReportReview(@PathVariable id: Long, @RequestBody reportDTO: ReportDTO, @RequestHeader("ApiKey") apikey: String): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.createReport(id, mapper!!.toEntity(reportDTO), apikey))
    }

}
