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

}
