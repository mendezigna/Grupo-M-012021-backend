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

        var review = Review(title= t1, overview= overview, description= description, rating= 1, date=date,
                user= user1, userNickname="Juan", spoiler = isSpoiler, premium = isPremium)

        Assertions.assertEquals(user1, review.user)
        Assertions.assertEquals("Juan", review.userNickname)
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
        var review = Review()
        Assertions.assertTrue( review.reports!!.isEmpty())
    }

    @Test
    fun addOneReportTest(){
        var review = Review(title= Title(), overview= "overview", description= "description", rating= 1, user= UserReview(), userNickname="Juan")
        val initialReportsAmount = review.reports?.size

        review.addReport(UserReview(),"reason")

        Assertions.assertTrue( initialReportsAmount!! < review.reports?.size!!)
    }

    @Test
    fun noLikesOnNewReviewTest(){
        var review = Review(title= Title(), overview= "overview", description= "description", rating= 1, user= UserReview(), userNickname="Juan")

        Assertions.assertEquals( 0, review.liking?.likes )
        Assertions.assertEquals( 0, review.liking?.dislikes )
    }

    @Test
    fun addLikeOnNewReviewTest(){
        var review = Review(title= Title(), overview= "overview", description= "description", rating= 1, user= UserReview(), userNickname="Juan")

        val userLikesIt = UserReview(Platform.Netflix,"pablo@example.com","Pablo")
        review!!.addLike(userLikesIt)

        Assertions.assertEquals(1,review.liking?.likes)
    }

    @Test
    fun addDislikeReviewTest(){
        var review = Review(title= Title(), overview= "overview", description= "description", rating= 1, user= UserReview(), userNickname="Juan")

        val userDislikes = UserReview(Platform.Netflix,"pablo@example.com","Pablo")
        review!!.addDislike(userDislikes)

        Assertions.assertEquals(1,review.liking?.dislikes)
    }

}