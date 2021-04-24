package unq.edu.ar.GrupoMs12021.Resenia.model.title.cast

import javax.persistence.*

@Entity
class Employee() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name : String? = null
    var category : Category? = null

    @ManyToOne
    var cast : Cast? = null

    constructor(name : String = "name", category: Category = Category.WRITER) : this(){
        this.name = name
        this.category = category
    }
}

