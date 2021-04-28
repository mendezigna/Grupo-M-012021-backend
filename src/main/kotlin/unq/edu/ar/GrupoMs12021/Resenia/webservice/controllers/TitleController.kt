package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleDTO

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/title")
class TitleController(@Autowired private val titleService: TitleService) {

    @GetMapping("{id}")
    fun getTitleByID(@PathVariable id: String): TitleDTO {
        return TitleDTO.fromModel(this.titleService.get(id))
    }
}