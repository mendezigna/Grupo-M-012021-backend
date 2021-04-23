package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType

data class TitleDTO(val titleId : String, val titleType: String, val name : String, val runTimeMinutes: Int, val startYear : Int, val endYear : Int?, val genres : List<String>){

    companion object {

        fun fromModel(title: Title):TitleDTO{
            return TitleDTO(title.titleId!!, title.titleType!!, title.name!!, title.runtimeMinutes!!, title.startYear!!, title.endYear, title.getGeneros().map{it.name})
        }
    }

}