package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.service.ClientService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ClientDTO
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Metrics
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.Log
import unq.edu.ar.GrupoMs12021.Resenia.webservice.config.JWTAuthorizationFilter.Companion.getJWTToken
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.RegisterDTO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.SubscriptionDTO


@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/client")
class ClientController(@Autowired private val clientService: ClientService) {

    @Log
    @PostMapping("/login")
    fun login(@RequestParam("username") username : String, @RequestParam("password") password: String) : ClientDTO {
        val token: String = getJWTToken(username)
        val user = clientService.login(username, password)
        val userDTO = ClientDTO.fromModel(user)
        userDTO.token = token
        return userDTO
    }

    @Log
    @PostMapping("/register")
    fun register(@RequestBody user : RegisterDTO) : ClientDTO{
        val userRegistered = clientService.register(Client(user.name, user.email, user.password, null))
        val token: String = getJWTToken(userRegistered.email!!)
        val userDTO = ClientDTO.fromModel(userRegistered)
        userDTO.token = token
        return userDTO
    }

    @Log
    @GetMapping("/metrics")
    fun getMetrics(@RequestParam email : String) : Metrics {
        return clientService.getMetrics(email)
    }

    @Log
    @PostMapping("/subscribe")
    fun subscribe(@RequestBody subscription : SubscriptionDTO) {
        clientService.subscribe(subscription)
    }


}

