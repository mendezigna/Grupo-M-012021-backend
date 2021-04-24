package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.*

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

    @OneToMany
    var user: UserReview? = null
    var userNickname: String? = null

    @OneToMany(cascade = [CascadeType.ALL])
    var reports: List<Report>? = listOf()
    var liking: Like? = null

    constructor() {}

}
