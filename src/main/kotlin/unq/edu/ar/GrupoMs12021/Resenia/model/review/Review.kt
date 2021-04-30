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
    var userNickname: String? = null

    @OneToMany(cascade = [CascadeType.ALL])
    var reports: List<Report>? = listOf()

    @OneToOne(cascade = [CascadeType.ALL])
    var liking: Liking? = null

    constructor() {}
    constructor(title:Title, overview:String, description:String, rating: Int, premium:Boolean? = false, user: UserReview, userNickname:String,
                spoiler:Boolean? = false, date:Date? = Date(), liking:Liking? = Liking(), reports:List<Report>? = listOf() ) {
        this.title = title
        this.rating = rating
        this.user = user
        this.userNickname = userNickname
        this.date = date
        this.description = description
        this.overview = overview
        this.premium = premium
        this.spoiler = spoiler
        this.reports = reports
        this.liking = liking
    }

    fun addReport(user: UserReview, reason: String): Report {
        var report = Report(this,user, reason)
        this.reports = this.reports!!.plus(report)
        return report
    }

    fun addLike(user: UserReview) {
        this.liking?.doLike(user)
    }

    fun addDislike(user: UserReview) {
        this.liking?.doDislike(user)
    }

}
