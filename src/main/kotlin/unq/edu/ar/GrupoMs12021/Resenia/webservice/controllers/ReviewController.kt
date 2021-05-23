package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.ApiKey
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewDTO

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService) {

    @ApiKey
    @GetMapping()
    fun getAll(): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getAll())
    }

    @ApiKey
    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.getById(id))
    }

    @ApiKey
    @PostMapping("/{id}/like")
    fun likeReview(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.addLiking(id, true))
    }

    @ApiKey
    @PostMapping("/{id}/dislike")
    fun dislikeReview(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.addLiking(id, false))
    }

    @ApiKey
    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getByTitleId(titleId))
    }

    @ApiKey
    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewDTO): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.create(ReviewDTO.fromService(reviewDTO), titleId))
    }
}