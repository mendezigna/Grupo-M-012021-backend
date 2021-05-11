package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService
import unq.edu.ar.GrupoMs12021.Resenia.service.UserReviewService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.UserReviewDTO
import java.util.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService) {

    @GetMapping()
    fun getAll(): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getAll())
    }

    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.getById(id))
    }

    @PostMapping("/{id}/like")
    fun likeReview(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.addLiking(id, true))
    }

    @PostMapping("/{id}/dislike")
    fun dislikeReview(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.addLiking(id, false))
    }

    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getByTitleId(titleId))
    }

    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewDTO): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.create(ReviewDTO.fromService(reviewDTO), titleId))
    }
}