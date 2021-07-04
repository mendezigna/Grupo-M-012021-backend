package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import java.io.Serializable

class TitleLite: Serializable {

    constructor(titleId: String, reviews: Int , rating: Float){
        this.titleId = titleId
        this.rating = rating
        this.reviews = reviews
    }

    var titleId: String? = null
//    var name : String? = null
    var rating: Float? = null
    var reviews: Int? = null

}
