package unq.edu.ar.GrupoMs12021.Resenia.model.title

import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import javax.persistence.*

@Entity
class Title() {

    companion object{
        fun createAnyTitle() : Title{

            return Title("tt00000",TitleBasicInformation(), listOf(), Cast())
        }

        fun createTitleWith(titleId: String = "", titleBasicInformation: TitleBasicInformation = TitleBasicInformation(), reviews: List<Review> = listOf(), cast : Cast = Cast()) : Title{
            return Title(titleId, titleBasicInformation, reviews,cast)
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null

    @Column(unique = true)
    var titleId: String? = null

    var titleType : String? = null
    var name : String? = null
    var startYear: Int? = null
    var endYear: Int? = null
    var runtimeMinutes: Int? = null
    private var genres: String = ""

    @OneToOne(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cast : Cast? = null

    @OneToMany(mappedBy = "title", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reviews: List<Review> = listOf()

    constructor(titleId: String, titleBasicInformation: TitleBasicInformation, reviews: List<Review>, cast : Cast) : this() {
        this.titleId = titleId
        this.titleType = titleBasicInformation.titleType.name
        this.name = titleBasicInformation.name
        this.startYear = titleBasicInformation.startYear
        this.endYear = titleBasicInformation.endYear
        this.runtimeMinutes = titleBasicInformation.runtimeMinutes
        setGenres(titleBasicInformation.genres)
        this.reviews = reviews
        this.cast = cast
        cast.setTitle(this)
    }

    fun setGenres(genres : List<Genres>){
        this.genres = genres.joinToString(";") { it.name }
    }

    fun getGenres(): List<Genres> {
        return this.genres.split(";").map { Genres.valueOf(it) }
    }

}

class TitleBasicInformation(val titleType: TitleType = TitleType.MOVIE, val name: String = "title", val startYear: Int = 2000, val endYear: Int? = null, val runtimeMinutes: Int = 100, val genres: List<Genres> = listOf())