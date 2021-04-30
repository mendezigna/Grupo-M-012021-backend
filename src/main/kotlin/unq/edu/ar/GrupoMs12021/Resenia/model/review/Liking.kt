package unq.edu.ar.GrupoMs12021.Resenia.model.review

import javax.persistence.*

@Entity
class Liking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ? = null

    @OneToOne
    var review: Review ? = null
    var likes: Int? = 0
    var dislikes: Int? = 0

    @ManyToMany(cascade = [CascadeType.ALL])
    var users: List<UserReview>? = listOf()

    constructor(review: Review?=null, likes: Int?= 0, dislikes: Int?= 0, users:List<UserReview>?= listOf()){
        this.review = review
        this.likes = likes
        this.dislikes = dislikes
        this.users = users
    }

    fun doLike(user: UserReview){
        addUser(user)
        likes = likes?.inc()
    }

    fun doDislike(user: UserReview){
        addUser(user)
        dislikes = dislikes?.inc()
    }

    private fun addUser(user: UserReview) {
        this.users = users?.plus(user)
    }

}
