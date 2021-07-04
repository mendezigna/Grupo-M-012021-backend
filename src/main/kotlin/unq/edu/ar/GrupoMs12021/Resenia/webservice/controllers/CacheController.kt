package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import unq.edu.ar.GrupoMs12021.Resenia.service.CacheService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/cache")
class CacheController(@Autowired private val cacheService: CacheService) {

    @GetMapping
    fun getByTitle(){
        this.cacheService.getByTitle("sadfadsf")
    }
}