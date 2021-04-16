package unq.edu.ar.GrupoMs12021.Resenia.model.review

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var name: String? = null

    constructor(name: String){
        this.name = name
    }
    constructor(){}
}
