package unq.edu.ar.GrupoMs12021.Resenia.model.title

import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import javax.persistence.*

@Entity
class Title() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(unique = true)
    var ttId : String? = null
    var ttType : TitleType? = null
    var name : String? = null
    var startYear: Int? = null
    var endYear: Int? = null
    var runtimeMinutes: Int? = null
    var genres: String? = null

    @OneToMany(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reviews: List<Review> = listOf()

    constructor(ttId: String, ttType: TitleType, name: String, startYear: Int, endYear: Int?, runtimeMinutes: Int, genres: List<Genres>) : this() {
        this.ttId = ttId
        this.ttType = ttType
        this.name = name
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        this.genres = genres.joinToString(";") { it.name }
    }

    fun getGenres(): List<Genres> {
        return this.genres!!.split(";").map { Genres.valueOf(it) }
    }
}