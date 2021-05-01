package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import javax.persistence.*

@Entity
class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ? = null
    var reason: String? = null
    @ManyToOne
    var review: Review? = null
    @ManyToOne
    var user: UserReview? = null

    constructor(review: Review, user: UserReview, reason: String, ){
        this.review = review
        this.reason = reason
        this.user = user
    }

}
