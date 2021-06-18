package unq.edu.ar.GrupoMs12021.Resenia.model.client

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import javax.persistence.*

@Entity
class Metrics() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne
    private var client: Client? = null

    var reviews : Int = 0
    var reports : Int = 0
    var likes : Int = 0
    var dislikes : Int = 0

    constructor(reviews : Int, reports : Int, likes: Int, dislikes : Int) : this(){
        this.reviews = reviews
        this.reports = reports
        this.likes = likes
        this.dislikes = dislikes
    }

    fun addReport() {
        reports += 1
    }

    fun addReview() {
        reviews += 1

    }

    fun addLike(like: Boolean) {
        if(like){
            likes += 1
        } else {
            dislikes += 1
        }
    }

    fun setClient(client : Client){
        this.client = client
    }

}