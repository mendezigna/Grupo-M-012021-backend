package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.service.ReviewService
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService
import unq.edu.ar.GrupoMs12021.Resenia.service.UserReviewService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.LikingDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ReviewDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.UserReviewDTO

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService,
                       private val userService: UserReviewService,
                       private val titleService: TitleService
) {

    @GetMapping()
    fun getAll(): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getAll())
    }

    @GetMapping("{id}")
    fun getByID(@PathVariable id: Long): ReviewDTO {
        return ReviewDTO.fromModel(this.reviewService.getById(id))
    }

    @GetMapping("/{id}/likes")
    fun getLikes(@PathVariable id: Long): LikingDTO {
        return LikingDTO.fromModel(this.reviewService.getById(id).liking!!)
    }

    @PostMapping("/{id}/likes/like")
    fun likeReview(@PathVariable id: Long, @RequestBody userdto: UserReviewDTO) {
        var review: Review = reviewService.getById(id)
        this.reviewService.addLiking(review, UserReviewDTO.fromService(userdto), true)
    }

    @PostMapping("/{id}/likes/dislike")
    fun dislikeReview(@PathVariable id: Long, @RequestBody userdto: UserReviewDTO) {
        var review: Review = reviewService.getById(id)
        var user = userService.getByIdPlatform(userdto.platform!!, userdto.platformID!!, userdto.nicknames!!)
        this.reviewService.addLiking(review, user, false)
    }

    @GetMapping("/title/{titleId}")
    fun getByTitleId(@PathVariable titleId: String): List<ReviewDTO> {
        return ReviewDTO.fromModel(this.reviewService.getByTitleId(titleId))
    }

    @PostMapping("/title/{titleId}")
    fun create(@PathVariable titleId: String, @RequestBody reviewDTO: ReviewDTO): ReviewDTO {
        var title: Title = titleService.get(titleId)
        var user: UserReview = this.userService.getByIdPlatform(reviewDTO.user?.platform!!, reviewDTO.user!!.platformID!!, reviewDTO.userNickname!!)
        var review: Review = ReviewDTO.fromService(reviewDTO)

        return ReviewDTO.fromModel(this.reviewService.save(review, title, user))
    }
}