package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.*

@Entity
class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var title: Title? = null
    var overview: String? = null
    var description: String? = null
    var rating: Int? = null
    var spoiler: Boolean? = null
    var premium: Boolean? = null
    var date: Date? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    var user: UserReview? = null

    @OneToMany(cascade = [CascadeType.ALL])
    var reports: List<Report>? = listOf()

    var likes: Int? = 0
    var dislikes: Int? = 0

    constructor() {}
    constructor(title: Title?, overview: String, description: String, rating: Int, premium: Boolean? = false, user: UserReview,
                spoiler: Boolean? = false, date: Date? = Date(), reports: List<Report>? = listOf(), likes: Int? = 0, dislikes: Int? = 0) {
        title?.let { this.setTitleReview(it) }
        this.rating = rating
        this.user = user
        this.date = date
        this.description = description
        this.overview = overview
        this.premium = premium
        this.spoiler = spoiler
        this.reports = reports
        this.likes = likes
        this.dislikes = dislikes
    }

    fun addReport(reason: String): Report {
        val report = Report(this, reason)
        this.reports = this.reports!!.plus(report)
        return report
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
