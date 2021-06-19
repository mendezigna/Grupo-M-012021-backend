package unq.edu.ar.GrupoMs12021.Resenia.model.client

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Subscription() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var titleId : String? = null
    var clientEmail : String? = null
    var url : String? = null

    constructor(url : String, titleId : String, email : String) : this(){
        this.titleId = titleId
        this.url = url
        this.clientEmail = email
    }
}