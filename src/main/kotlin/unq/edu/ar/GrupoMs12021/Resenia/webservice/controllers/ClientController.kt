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
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*


@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/client")
class ClientController(@Autowired private val clientService: ClientService) {

    @PostMapping("/login")
    fun login(@RequestParam("username") username : String, @RequestParam("password") password: String) : ClientDTO {
        val token: String = getJWTToken(username)
        val user = clientService.login(username, password)
        val userDTO = ClientDTO.fromModel(user)
        userDTO.token = token
        return userDTO
    }

    @PostMapping("/register")
    fun register(@RequestBody user : Client) : ClientDTO{
        val userRegistered = clientService.register(user)
        val token: String = getJWTToken(userRegistered.email!!)
        val userDTO = ClientDTO.fromModel(user)
        userDTO.token = token
        return userDTO
    }

    @GetMapping("/test")
    fun test() : ResponseEntity<String> {
        return ResponseEntity("hola", HttpStatus.OK)
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