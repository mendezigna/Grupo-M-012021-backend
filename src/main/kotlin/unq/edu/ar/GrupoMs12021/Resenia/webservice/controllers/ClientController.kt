package unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers

import com.nimbusds.jwt.JWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.web.bind.annotation.*
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.service.ClientService
import unq.edu.ar.GrupoMs12021.Resenia.webservice.dto.ClientDTO
import java.util.stream.Collectors
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.hibernate.mapping.Constraint
import org.hibernate.validator.constraints.URL
import org.intellij.lang.annotations.Pattern
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry
import org.springframework.validation.annotation.Validated
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Metrics
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.Log
import java.util.*
import javax.swing.SpringLayout
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


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

    private fun getJWTToken(username: String): String {
        val secretKey = "secretKeyMuySecreta"
        val grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER")
        val token: String = Jwts
            .builder()
            .setId("JWT")
            .setSubject(username)
            .claim("authorities",
                grantedAuthorities.stream()
                    .map { obj: GrantedAuthority -> obj.authority }
                    .collect(Collectors.toList()))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 600000))
            .signWith(
                SignatureAlgorithm.HS512,
                secretKey.toByteArray()
            ).compact()
        return "Bearer $token"
    }
}

class RegisterDTO(val email: String, val password: String, val name: String)

class SubscriptionDTO(val email: String, val url : String, val titleId : String)