package unq.edu.ar.GrupoMs12021.Resenia.model.title

import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import javax.persistence.*

@Entity
class Title() {

    companion object{
        fun createAnyTitle() : Title{

            return Title("tt00000", TitleType.MOVIE,"Title", 2000, null, 60, "", listOf(), Cast())
        }

        fun createTitleWith(titleId: String = "", titleBasicInformation: TitleBasicInformation = TitleBasicInformation(), reviews: List<Review> = listOf(), cast : Cast = Cast()) : Title{
            return Title(titleId, titleBasicInformation.titleType, titleBasicInformation.name, titleBasicInformation.startYear, titleBasicInformation.endYear, titleBasicInformation.runtimeMinutes, titleBasicInformation.genres.joinToString(";") { it.name }, reviews,cast)
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    @Column(unique = true)
    var titleId: String? = null

    var titleType : String? = null
    var name : String? = null
    var startYear: Int? = null
    var endYear: Int? = null
    var runtimeMinutes: Int? = null
    var genres: String = ""

    @OneToOne(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cast : Cast? = null

    @OneToMany(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reviews: List<Review> = listOf()

    constructor(titleId: String, titleType: TitleType, name : String, startYear: Int, endYear: Int?, runtimeMinutes: Int, genres: String, reviews: List<Review>, cast : Cast) : this() {
        this.titleId = titleId
        this.titleType = titleType.name
        this.name = name
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        this.genres = genres
        this.reviews = reviews
        this.cast = cast
        cast.setTitle(this)
    }

    fun setGeneross(genres : List<Genres>){
        this.genres = genres.joinToString(";") { it.name }
    }

    fun getGeneros(): List<Genres> {
        return this.genres.split(";").map { Genres.valueOf(it) }
    }

}

class TitleBasicInformation(val titleType: TitleType = TitleType.MOVIE, val name: String = "title", val startYear: Int = 2000, val endYear: Int? = null, val runtimeMinutes: Int = 100, val genres: List<Genres> = listOf())