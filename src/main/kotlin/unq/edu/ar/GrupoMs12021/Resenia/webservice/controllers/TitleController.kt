package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.ApiKey
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleDTO

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/title")
class TitleController(@Autowired private val titleService: TitleService) {

    @ApiKey
    @GetMapping("{id}")
    fun getTitleByID(@PathVariable id: String): TitleDTO {
        return TitleDTO.fromModel(this.titleService.get(id))
    }
}