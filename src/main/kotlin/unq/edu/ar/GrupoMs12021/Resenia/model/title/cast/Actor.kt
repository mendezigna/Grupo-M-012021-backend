package unq.edu.ar.GrupoMs12021.Resenia.model.title.cast

import javax.persistence.*

@Entity
class Actor() {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name : String? = null
    private var characters : String = ""

    @ManyToOne
    val cast : Cast? = null

    constructor(name: String = "name", characters: List<String> = listOf()) : this() {
        this.name = name
        setCharacters(characters)
    }

    fun setCharacters(characters : List<String>){
        this.characters = characters.joinToString(";")
    }

    fun getCharacters() : List<String>{
        return this.characters.split(";")
    }

}