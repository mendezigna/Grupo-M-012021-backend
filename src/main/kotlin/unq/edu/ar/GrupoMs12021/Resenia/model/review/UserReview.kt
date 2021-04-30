package unq.edu.ar.GrupoMs12021.Resenia.model.review

import javax.persistence.*

@Entity
class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var platformID: String? = null
    @Enumerated(value = EnumType.STRING)
    var platform: Platform? = null
    var nicknames: String? = null
    var location: String? = null
    var language: String? = null
    @OneToMany
    var reviews: List<Review>? = listOf()

    constructor(){}
    constructor(platform: Platform, platformID: String, nicknames:String, location: String?="" , language: String?="", reviews: List<Review>? = listOf() ){
        this.platform = platform
        this.platformID = platformID
        this.nicknames = nicknames
        this.location = location
        this.language = language
        this.reviews = reviews
    }

    fun addReview(review: Review) {
        this.reviews = reviews?.plus(review)
    }

}
