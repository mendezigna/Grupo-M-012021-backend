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
    val overview: String? = null
    val description: String? = null
    val rating: Integer? = null
    val spoiler: Boolean? = null
    val premium: Boolean? = null
    val date: Date? = null
    @ManyToOne
    val origin: Platform? = null
    val usrNickname: String? = null
    val usrLocation: String? = null
    val usrLanguage: String? = null

    constructor(){}
}