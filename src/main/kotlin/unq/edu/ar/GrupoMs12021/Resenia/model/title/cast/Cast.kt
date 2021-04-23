package unq.edu.ar.GrupoMs12021.Resenia.model.title.cast

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import javax.persistence.*


@Entity
class Cast() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var director: String? = null

    @OneToMany(mappedBy = "cast",cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var actors: List<Actor> = listOf()

    @OneToMany(mappedBy = "cast",cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var employees : List<Employee> = listOf()

    @OneToOne
    var title : Title? = null

    constructor(title : Title,director: String = "director", actors: List<Actor> = listOf(), employees: List<Employee> = listOf()) : this(){
        this.title = title
        this.director = director
        this.actors = actors
        this.employees = employees
    }
}