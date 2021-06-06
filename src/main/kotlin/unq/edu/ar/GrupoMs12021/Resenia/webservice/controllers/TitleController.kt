package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleDTO
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.TitleFilter
@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/titles")
class TitleController(@Autowired private val titleService: TitleService) {

//    @ApiKey
    @GetMapping
    fun getAll(@RequestParam(required = false) numPage:Int?,
               @RequestParam(required = false) sizePage:Int?,
               @RequestParam(required = false) titleType:String?,
               @RequestParam(required = false) genre:Genres?,
               @RequestParam(required = false) titleName:String?,
               @RequestParam(required = false) director:String?,
               @RequestParam(required = false) actor:String?,
               @RequestParam(required = false) decade:Int?,
               @RequestParam(required = false) rating:Float?,
               @RequestParam(required = false) likesGT:String?,
//               req: HttpServletRequest
    ): List<TitleDTO> {
        val titleFilter = TitleFilter(numPage, sizePage, titleType, genre, titleName, director, actor, decade, rating, likesGT)
        return this.titleService.getAll(titleFilter).map { t:Title -> TitleDTO.fromModel(t) }
    }

//    @ApiKey
    @GetMapping("{id}")
    fun getTitleByID(@PathVariable id: String): TitleDTO {
        return TitleDTO.fromModel(this.titleService.get(id))
    }
}
