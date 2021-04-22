package unq.edu.ar.GrupoMs12021.Resenia.model.title.cast

import javax.persistence.*


@Entity
class Cast() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var director: String? = null

    @OneToMany(mappedBy = "cast",cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var actors: List<Actor> = listOf()

    @OneToMany(mappedBy = "cast",cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var employees : List<Employee> = listOf()

    constructor(director: String = "director", actors: List<Actor> = listOf(), employees: List<Employee> = listOf()) : this(){
        this.director = director
        this.actors = actors
        this.employees = employees
    }
}