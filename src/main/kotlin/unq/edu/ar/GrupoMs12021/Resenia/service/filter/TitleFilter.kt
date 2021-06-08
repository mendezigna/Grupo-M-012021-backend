package unq.edu.ar.GrupoMs12021.Resenia.service.filter

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType

class TitleFilter {
    val likesGT: Int?
    val rating: Float?
    val decade: Int?
    val actor: String?
    val director: String?
    val titleName: String?
    val genre: Genres?
    val titleType: TitleType?
    var numPage: Int
    var sizePage: Int

    constructor(numPage: Int?, sizePage: Int?, titleType: TitleType?, genre: Genres?, titleName: String?, director: String?, actor: String?, decade: Int?, rating: Float?, likesGT: Int?){
        this.numPage = numPage ?: 0
        this.sizePage =  sizePage ?: 15
        this.titleType = titleType
        this.genre = genre
        this.titleName = titleName
        this.director = director
        this.actor = actor
        this.decade = decade
        this.rating = rating
        this.likesGT = likesGT
    }

    fun buildFilterMap(): MutableMap<String,Any> {
        var map = mutableMapOf<String,Any>()
        if (this.titleType!=null){ map.put(TitleFields.titleType, this.titleType) }
        if (this.titleName !=null){ map.put(TitleFields.titleName, this.titleName) }
        if (this.genre!=null){ map.put(TitleFields.genre, this.genre) }
        if (this.actor!=null){ map.put(TitleFields.actor, this.actor) }
        if (this.director!=null){ map.put(TitleFields.director, this.director) }
        if (this.decade!=null){ map.put(TitleFields.decade, this.decade) }
        if (this.rating!=null){ map.put(TitleFields.rating, this.rating) }
        if (this.likesGT!=null){ map.put(TitleFields.likesGT, this.likesGT) }
        return map
    }

}
