package unq.edu.ar.GrupoMs12021.Resenia.service.filter

import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PremiumReview
import unq.edu.ar.GrupoMs12021.Resenia.model.review.PublicReview
import java.util.*

class ReviewFilter {

    val numPage: Int
    val sizePage: Int
    var sort: String? = null
    var order: String? = null
    var platform: Platform?
    var rating: Float?
    var spoiler: Boolean?
    var reviewType: Any?
    var language: String?
    var location: String?
    var date: Date?
    var titleId: String?

    constructor(numPage: Int?, sizePage: Int?, orderBy: String?, platform: Platform?, rating: Float?, spoiler: Boolean?, reviewType:String?, language: String?, location: String?, date: Date?, titleId: String?= null){
        this.numPage = numPage ?: 0
        this.sizePage = sizePage ?: 15
        setSortAndOrder(orderBy)
        this.platform = platform
        this.rating = rating
        this.spoiler = spoiler
        this.reviewType = setType(reviewType)
        this.language = language
        this.location = location
        this.date = date
        this.titleId = titleId
    }

    companion object {
        fun createEmptyFilter(): ReviewFilter {
            return ReviewFilter(null, null, null, null, null, null, null, null, null, null)
        }
    }

    fun buildFilterMap(): MutableMap<String,Any> {
        var map = mutableMapOf<String, Any>()

        // no reports reviews : ToDO
//        map.put(FiltersReviewFields.hasNoReports, "" )

        if (this.platform != null) { map.put(FiltersReviewFields.platform, this.platform!!) }
        if (this.spoiler != null) { map.put(FiltersReviewFields.spoiler,this.spoiler!! ) }
        if (this.reviewType != null) { map.put(FiltersReviewFields.reviewType,this.reviewType!! ) }
        if (this.language != null) { map.put(FiltersReviewFields.language,this.language!! ) }
        if (this.location != null) { map.put(FiltersReviewFields.location,this.location!! ) }
        if (this.date != null) { map.put(FiltersReviewFields.date,this.date!! ) }
        if (this.titleId != null) { map.put(FiltersReviewFields.titleID,this.titleId!! ) }
        if (this.rating != null) { map.put(FiltersReviewFields.rating,this.rating!! ) }

        return map
    }

    private fun setType(reviewType: String?): Any? {
        return when(reviewType){
            "PublicReview" -> PublicReview::class.java //as Review::class.java
            "PremiumReview" -> PremiumReview::class.java //as Review::class.java
            else -> null
        }
    }

    private fun setSortAndOrder(orderBy: String?) {
        if (checkSortField(orderBy) &&
                (orderBy!!.contains("asc") || orderBy.contains("desc"))) {
            this.sort = orderBy.split(",")[0]
            this.order = orderBy.split(",")[1]
        } else {
            this.sort = "date"
            this.order = "desc"
        }
    }
    private fun checkSortField(sortBy: String?) =
            sortBy != null &&
                    (sortBy.contains("date") || sortBy.contains("rating"))

}

