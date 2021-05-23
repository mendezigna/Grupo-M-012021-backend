package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.service.ClientService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/client")
class ClientController(@Autowired private val clientService: ClientService) {

    @PostMapping("/login")
    fun login(@RequestBody user : Client) : Client{
        return clientService.login(user)
    }

    @PostMapping("/register")
    fun register(@RequestBody user : Client) : Client{
        return clientService.register(user)
    }

    @GetMapping("/test")
    fun test() : String {
        return "Test"
    }
}