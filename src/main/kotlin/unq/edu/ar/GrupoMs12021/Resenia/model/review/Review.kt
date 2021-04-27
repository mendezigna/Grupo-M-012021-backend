package unq.edu.ar.GrupoMs12021.Resenia.model.review

import jdk.jfr.Description
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
class Review {

    @Id
    var id: Long? = null

    @ManyToOne
    var title: Title? = null
    var overview: String? = null
    var description: String? = null
    var rating: Int? = null
    var spoiler: Boolean? = null
    var premium: Boolean? = null
    var date: Date? = null

    @ManyToOne
    var user: UserReview? = null
    var userNickname: String? = null

    @OneToMany(cascade = [CascadeType.ALL])
    var reports: List<Report>? = listOf()

    @OneToOne
    var liking: Liking? = null

    constructor() {}
    constructor(title:Title, overview:String, description:String, rating: Int, premium:Boolean, user: UserReview, userNickname:String,
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

}
