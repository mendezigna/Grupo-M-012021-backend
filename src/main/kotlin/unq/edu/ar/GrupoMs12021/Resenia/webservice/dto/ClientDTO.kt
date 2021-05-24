package unq.edu.ar.GrupoMs12021.Resenia.webservice.dto

import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client

class ClientDTO(var token : String? = null, var username : String, var name: String, var apikey: String) {
    companion object{
        fun fromModel(user: Client) : ClientDTO{
            return ClientDTO(null, user.email!! ,user.name!!, user.apyKey!!)
        }
    }
}