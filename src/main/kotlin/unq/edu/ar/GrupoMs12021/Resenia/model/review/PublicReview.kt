package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class PublicReview: Review {

    @OneToMany(cascade = [CascadeType.ALL])
    var reports: List<Report>? = listOf()
    var nicknames: String? = null
    var location: String? = null
    var spoiler: Boolean? = false

    constructor(){}
    constructor(title: Title?, overview: String, description: String, rating: Float, language: String,
                platform: Platform, platformId: String, nickname: String, location: String,
                date: Date? = Date(), spoiler: Boolean? = false, likes: Int? = 0, dislikes: Int? = 0
    ) : super() {
        title?.let { this.setTitleReview(it) }
        this.rating = rating
        this.date = date
        this.description = description
        this.overview = overview
        this.likes = likes
        this.dislikes = dislikes
        this.spoiler = spoiler
        this.platform = platform
        this.platformID = platformId
        this.language = language
        this.nicknames = nickname
        this.location = location
    }

    fun addReport(reason: String): Report {
        val report = Report(this, reason)
        this.reports = this.reports!!.plus(report)
        return report
    }
}