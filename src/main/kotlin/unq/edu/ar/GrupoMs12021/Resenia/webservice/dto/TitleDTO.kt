package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Actor
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Category
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Employee

data class TitleDTO(val titleId : String, val titleType: TitleType, val name : String, val runTimeMinutes: Int, val startYear : Int, val endYear : Int?, val genres : List<Genres>, val cast : CastDTO?){

    companion object {

        fun fromModel(title: Title):TitleDTO{
            return TitleDTO(title.titleId!!, title.titleType!!, title.name!!, title.runtimeMinutes!!, title.startYear!!, title.endYear, title.getGenres(), CastDTO.fromModel(title.cast!!))
        }
    }

}

data class CastDTO(val director : String, val actors : List<ActorDTO>, val employees : List<EmployeeDTO>){
    
    companion object{
        fun fromModel(cast : Cast) : CastDTO{
            return CastDTO(cast.director!!, cast.actors.map { ActorDTO.fromModel(it) }, cast.employees.map { EmployeeDTO.fromModel(it) })
        }
    }
}

data class ActorDTO(val name : String, val characters : List<String>){
    companion object{
        fun fromModel(actor : Actor) : ActorDTO{
            return ActorDTO(actor.name!!, actor.getCharacters())
        }
    }
}

data class EmployeeDTO(val name : String, val category: Category){
    companion object{
        fun fromModel(employee : Employee) : EmployeeDTO{
            return EmployeeDTO(employee.name!!, employee.category!!)
        }
    }
}