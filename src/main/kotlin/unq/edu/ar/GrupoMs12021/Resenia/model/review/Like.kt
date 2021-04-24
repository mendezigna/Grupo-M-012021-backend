package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Entity

@Entity
class Like {

    @Id
    var id: Long ? = null

    @OneToOne
    var review: Review ? = null
    var likes: List<Client>? = listOf()
    var dislikes: List<Client>? = listOf()


    fun like(client: Client){
        //add Like
    }

    fun dislike(client: Client){
        // add dislike
    }

}
