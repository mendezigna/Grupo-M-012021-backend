package unq.edu.ar.GrupoMs12021.Resenia.model.review

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*

class ReviewsUnitTest {

    @Test
    fun createReviewTest(){
        val pl = Platform.Netflix
        val plId = "juan@example.com"
        val plNick = "Juan"
        val lang = "ES"
        val loc = "BsAs, Argentina"
        val t1: Title = Title.createAnyTitle()
        val overview = "Some overview"
        val description = "Some description"
        val date = Date()
        val isSpoiler = false

        val review = PublicReview(title = t1, overview = overview, description = description, rating = 1f, date =date,
                spoiler = isSpoiler, language = lang, platform = pl, platformId = plId, nickname = plNick, location = loc)
        Assertions.assertEquals(1.0f, review.rating)
        Assertions.assertEquals(overview, review.overview)
        Assertions.assertEquals(date, review.date)
        Assertions.assertEquals(description, review.description)
        Assertions.assertEquals(t1, review.title)
        Assertions.assertEquals(isSpoiler, review.spoiler)
        Assertions.assertEquals(lang, review.language)
        Assertions.assertEquals(pl, review.platform)
        Assertions.assertEquals(plId, review.platformID)
        Assertions.assertEquals(plNick, review.nicknames)
        Assertions.assertEquals(loc, review.location)
    }

    @Test
    fun noReportsOnJustCreatedReviewTest(){
        val review = PublicReview()
        Assertions.assertTrue( review.reports!!.isEmpty())
    }

    @Test
    fun addOneReportTest(){
        val review = PublicReview(title = Title(), overview = "overview", description = "description", rating = 1f,
                language = "ES", platform =  Platform.Netflix, platformId =  "jon.snow", nickname =  "unNick", location = "ARG")
        val initialReportsAmount = review.reports?.size

        review.addReport("reason")

        Assertions.assertTrue( initialReportsAmount!! < review.reports?.size!!)
    }

    @Test
    fun noLikesOnNewReviewTest(){
        val review = PublicReview(title = Title(), overview = "overview", description = "description", rating = 1f,
                language = "ES", platform =  Platform.Netflix, platformId =  "jon.snow", nickname =  "unNick", location = "ARG")

        Assertions.assertEquals( 0, review.likes )
        Assertions.assertEquals( 0, review.dislikes )
    }

    @Test
    fun addLikeOnNewReviewTest(){
        val review = PublicReview(title = Title(), overview = "overview", description = "description", rating = 1f,
                language = "ES", platform =  Platform.Netflix, platformId =  "jon.snow", nickname =  "unNick", location = "ARG")

        val likeIt = true
        review.addLike(likeIt)

        Assertions.assertEquals(1,review.likes)
    }

    @Test
    fun addDislikeReviewTest(){
        val review = PublicReview(title = Title(), overview = "overview", description = "description", rating = 1f,
                language = "ES", platform =  Platform.Netflix, platformId =  "jon.snow", nickname =  "unNick", location = "ARG")
        val dislikeIt = false
        review.addLike(dislikeIt)

        Assertions.assertEquals(1,review.dislikes)
    }


    /** Review Premium, realizadas por cri'ticos **/

    @Test
    fun createPremiumReviewTest(){
        val t1: Title = Title.createAnyTitle()
        val pl = Platform.Netflix
        val plId = "juan@example.com"
        val adate: Date = Date()

        val review = PremiumReview(t1, "overview", "descript", 5f, "ES", pl, plId, adate)
        Assertions.assertEquals(5f, review.rating)
        Assertions.assertEquals("overview", review.overview)
        Assertions.assertEquals(adate, review.date)
        Assertions.assertEquals("descript", review.description)
        Assertions.assertEquals("ES", review.language)
        Assertions.assertEquals(t1, review.title)
        Assertions.assertEquals(pl, review.platform)
        Assertions.assertEquals(plId, review.platformID)
    }

}