package unq.edu.ar.GrupoMs12021.Resenia.model

import javax.persistence.*

@Entity
class Client(val name : String, @Column(unique = true) val email: String, val password: String, @Column(unique = true) val apyKey : String?) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}