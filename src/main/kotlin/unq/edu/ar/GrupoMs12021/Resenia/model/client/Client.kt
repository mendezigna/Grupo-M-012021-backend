package unq.edu.ar.GrupoMs12021.Resenia.model.client

import javax.persistence.*

@Entity
class Client() {

    var name : String? = null
    @Column(unique = true)
    lateinit var email: String
    lateinit var password: String
    @Column(unique = true)
    var apyKey : String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(name: String?, email: String, password: String, apyKey: String?) : this(){
        this.name = name
        this.email = email
        this.password = password
        this.apyKey = apyKey
    }
}

