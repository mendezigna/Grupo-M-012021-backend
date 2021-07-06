package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.service.CacheService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.ApiKey
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.Log
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.TitleLite

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/cache")
class CacheController(@Autowired private val cacheService: CacheService) {

    @Log
    @GetMapping("{titleId}")
    fun getByTitle(@PathVariable titleId: String): TitleLite? {
        return this.cacheService.getByTitle(titleId)
    }
}