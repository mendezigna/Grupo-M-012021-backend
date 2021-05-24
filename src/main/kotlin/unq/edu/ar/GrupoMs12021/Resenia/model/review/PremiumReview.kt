package unq.edu.ar.GrupoMs12021.Resenia.model.review

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*
import javax.persistence.Entity

@Entity
class PremiumReview(title: Title?, overview: String, description: String, rating: Int, language: String,  platform: Platform?,
                    platformID: String, date: Date? = Date(), likes: Int? = 0, dislikes: Int? = 0) :
        Review(title, overview, description, rating, platform, platformID, language, date, likes, dislikes) {

 }
