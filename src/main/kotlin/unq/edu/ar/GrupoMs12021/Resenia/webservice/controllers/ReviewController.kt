package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleDTO

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService) {

    @GetMapping()
    fun getAll(): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getAll())
    }

    @GetMapping("{id}")
    fun getByID(@PathVariable id: String): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.getById(id))
    }

    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getByTitleId(titleId))
    }

}