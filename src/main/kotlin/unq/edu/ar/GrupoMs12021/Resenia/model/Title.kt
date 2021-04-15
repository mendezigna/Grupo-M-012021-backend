package unq.edu.ar.GrupoMs12021.Resenia.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import javax.persistence.*

@Entity
class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: String? = null
    val ttType : TitleType
    val name : String
    val startYear: LocalDate
    val endYear: LocalDate?
    val runtimeMinutes: Int
    val genres: String

    @OneToMany(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reviews: List<Review> = listOf()

    constructor(ttId: String, ttType: TitleType, name: String, startYear: LocalDate, endYear: LocalDate?, runtimeMinutes: Int, genres: List<Genres>){
        this.id = ttId

        this.ttType = ttType
        this.name = name
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        this.genres = genres.joinToString(";") { it.name }
    }

    fun getGenres(): List<Genres> {
        return this.genres.split(";").map { Genres.valueOf(it) }
    }
}