package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import javax.persistence.*

@Entity
class Liking {

    @Id
    var id: Long ? = null

    @OneToOne
    var review: Review ? = null

//    @ManyToMany
    @Transient
    var likes: List<Client>? = listOf()
//    @ManyToMany
    @Transient
    var dislikes: List<Client>? = listOf()

    constructor(){}
    constructor(review: Review, likes: List<Client> ? = listOf(), dislikes: List<Client> ? = listOf()){
        this.review = review
        this.likes = likes
        this.dislikes = dislikes
    }

    fun likeReview(client: Client){
        //add Like
    }

    fun dislikeReview(client: Client){
        // add dislike
    }

}
