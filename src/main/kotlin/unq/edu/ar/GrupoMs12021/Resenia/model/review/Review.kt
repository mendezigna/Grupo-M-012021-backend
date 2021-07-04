package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.*

@Entity
open class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @ManyToOne
    open var title: Title? = null
    open var overview: String? = null
    open var description: String? = null
    open var rating: Float? = null
//    @Basic
//    @Temporal(TemporalType.TIMESTAMP)
    open var date: Date? = null
    open var likes: Int? = 0
    open var dislikes: Int? = 0
    open var platform: Platform? = null
    open var platformID: String? = null
    open var language: String? = null

    constructor() {}
    constructor(title: Title?, overview: String, description: String, rating: Float,
            platform: Platform?, platformID: String, language: String,
            date: Date? = Date(),likes: Int? = 0, dislikes: Int? = 0) {
        title?.let { this.setTitleReview(it) }
        this.rating = rating
        this.date = date
        this.description = description
        this.overview = overview
        this.likes = likes
        this.dislikes = dislikes
        this.platform = platform
        this.platformID = platformID
        this.language = language
    }

    fun addLike(isLike: Boolean) {
        if (isLike) {
            likes = likes?.inc()
        } else {
            dislikes = dislikes?.inc()
        }
    }

    fun setTitleReview(title: Title) {
        this.title = title
        title.addReview(this)
    }

}
