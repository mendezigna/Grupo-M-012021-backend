package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewGenericDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewMapper

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/reviews")
class ReviewController(private val reviewService: ReviewService) {

    @Autowired
    var mapper: ReviewMapper? = ReviewMapper()

    @GetMapping()
    fun getAll(): List<ReviewGenericDTO> {
        return  this.reviewService.getAll().map { rev: Review -> mapper!!.toDto(rev) }
    }

    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.getById(id))
    }

    @PostMapping("/{id}/like")
    fun likeReview(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, true))
    }

    @PostMapping("/{id}/dislike")
    fun dislikeReview(@PathVariable id: Long): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.addLiking(id, false))
    }

    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewGenericDTO> {
        return this.reviewService.getByTitleId(titleId).map { rev: Review -> mapper!!.toDto(rev) }
    }

    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewGenericDTO): ReviewGenericDTO {
        return this.mapper!!.toDto(this.reviewService.create(mapper!!.toEntity(reviewDTO), titleId))
    }

}
