package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/title")
class TitleController(private val titleService: TitleService) {

    @GetMapping("/get/{id}")
    fun getTitleByID(@PathVariable id: String): Title {
        return this.titleService.get(id)
    }
}