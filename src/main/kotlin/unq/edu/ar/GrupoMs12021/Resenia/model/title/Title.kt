package unq.edu.ar.GrupoMs12021.Resenia.model.title

import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import javax.persistence.*

@Entity
class Title() {

    companion object{
        fun createAnyTitle() : Title{
            val cast = Cast()
            val title = Title("tt00000", TitleType.MOVIE, "title", 2000, null,100, listOf(), listOf())
            title.setCast(cast)
            return title
        }

        fun createTitleWith(titleId: String = "", titleBasicInformation: TitleBasicInformation = TitleBasicInformation(), reviews: List<Review> = listOf(), cast : Cast = Cast()) : Title{
            val title = Title(titleId, titleBasicInformation.titleType, titleBasicInformation.name, titleBasicInformation.startYear, titleBasicInformation.endYear, titleBasicInformation.runtimeMinutes, titleBasicInformation.genres, reviews)
            cast.title = title
            title.cast = cast
            return title
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null

    @Column(unique = true)
    var titleId: String? = null

    var titleType : TitleType? = null
    var name : String? = null
    var startYear: Int? = null
    var endYear: Int? = null
    var runtimeMinutes: Int? = null
    private var genres: String = ""

    @OneToOne(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var cast : Cast? = null

    @OneToMany(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reviews: List<Review> = listOf()

    constructor(titleId: String, ttType: TitleType, name: String, startYear: Int, endYear: Int?, runtimeMinutes: Int, genres: List<Genres>, reviews: List<Review>) : this() {
        this.titleId = titleId
        this.titleType = ttType
        this.name = name
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        setGenres(genres)
        this.reviews = reviews
    }

    fun setGenres(genres : List<Genres>){
        this.genres = genres.joinToString(";") { it.name }
    }

    fun getGenres(): List<Genres> {
        return this.genres.split(";").map { Genres.valueOf(it) }
    }

    fun setCast(cast: Cast){
        cast.title = this
        this.cast = cast
    }

}

class TitleBasicInformation(val titleType: TitleType = TitleType.MOVIE, val name: String = "title", val startYear: Int = 2000, val endYear: Int? = null, val runtimeMinutes: Int = 100, val genres: List<Genres> = listOf())