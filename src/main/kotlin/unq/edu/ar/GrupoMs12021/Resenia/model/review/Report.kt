package unq.edu.ar.GrupoMs12021.Resenia.model.review

import javax.persistence.*

@Entity
class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ? = null
    var reason: String? = null
    @ManyToOne
    var review: PublicReview? = null

    constructor(review: PublicReview, reason: String ){
        this.review = review
        this.reason = reason
    }

}
