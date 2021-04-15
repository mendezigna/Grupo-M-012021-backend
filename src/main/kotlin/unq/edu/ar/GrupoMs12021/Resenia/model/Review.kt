package unq.edu.ar.GrupoMs12021.Resenia.model

import javax.persistence.*

@Entity
class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var title: Title? = null

    constructor(){}
}