package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Metrics

class ClientDTO(var token : String? = null, var email : String, var name: String, var apikey: String) {
    companion object{
        fun fromModel(user: Client) : ClientDTO{
            return ClientDTO(null, user.email!! ,user.name!!, user.apyKey!!)
        }
    }
}

class RegisterDTO(val email: String, val password: String, val name: String)

class SubscriptionDTO(val email: String, val url : String, val titleId : String)