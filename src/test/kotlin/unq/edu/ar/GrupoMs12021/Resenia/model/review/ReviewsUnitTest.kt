package unq.edu.ar.GrupoMs12021.Resenia.model.review

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import java.util.*

class ReviewsUnitTest {

    @Test
    fun createReviewTest(){
        val user1 = UserReview(Platform.Netflix,"juan@example.com","Juan")
        val t1: Title = Title.createAnyTitle()
        val overview = "Some overview"
        val description = "Some description"
        val date = Date()
        val isSpoiler = false
        val isPremium = false

        val review = Review(title = t1, overview = overview, description = description, rating = 1, date =date,
                user = user1, spoiler = isSpoiler, premium = isPremium)

        Assertions.assertEquals(user1, review.user)
        Assertions.assertEquals(1, review.rating)
        Assertions.assertEquals(overview, review.overview)
        Assertions.assertEquals(date, review.date)
        Assertions.assertEquals(description, review.description)
        Assertions.assertEquals(t1, review.title)
        Assertions.assertEquals(isPremium, review.premium)
        Assertions.assertEquals(isSpoiler, review.spoiler)
    }

    @Test
    fun noReportsOnJustCreatedReviewTest(){
        val review = Review()
        Assertions.assertTrue( review.reports!!.isEmpty())
    }

    @Test
    fun addOneReportTest(){
        var review = Review(title = Title(), overview = "overview", description = "description", rating = 1, user = UserReview())
        val initialReportsAmount = review.reports?.size

        review.addReport("reason")

        Assertions.assertTrue( initialReportsAmount!! < review.reports?.size!!)
    }

    @Test
    fun noLikesOnNewReviewTest(){
        val review = Review(title = Title(), overview = "overview", description = "description", rating = 1, user = UserReview())

        Assertions.assertEquals( 0, review.likes )
        Assertions.assertEquals( 0, review.dislikes )
    }

    @Test
    fun addLikeOnNewReviewTest(){
        var review = Review(title = Title(), overview = "overview", description = "description", rating = 1, user = UserReview())

        val likeIt = true
        review.addLike(likeIt)

        Assertions.assertEquals(1,review.likes)
    }

    @Test
    fun addDislikeReviewTest(){
        var review = Review(title = Title(), overview = "overview", description = "description", rating = 1, user = UserReview())

        val userDislikes = UserReview(Platform.Netflix,"pablo@example.com","Pablo")
        val dislikeIt = false
        review!!.addLike(dislikeIt)

        Assertions.assertEquals(1,review.dislikes)
    }

}