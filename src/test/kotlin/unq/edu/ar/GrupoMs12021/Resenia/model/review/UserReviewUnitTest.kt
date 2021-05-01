package unq.edu.ar.GrupoMs12021.Resenia.model.review

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title

class UserReviewUnitTest {

    @Test
    fun createUserReview() {

        val reviews = emptyList<Review>()
        val netflix = Platform.Netflix
        val netflixUserId ="juan@example.com"
        val location = "BsAs, Argentina"
        val nickname = "Juan"
        val user1 = UserReview(netflix,netflixUserId,nickname, location, "Spanish", reviews)

        Assertions.assertEquals(user1.platform, netflix)
        Assertions.assertEquals(user1.platformID, netflixUserId)
        Assertions.assertEquals(user1.nicknames, nickname)
        Assertions.assertEquals(user1.location, location)
        Assertions.assertEquals(user1.language, "Spanish")
        Assertions.assertEquals(user1.reviews, reviews)
    }

    @Test
    fun userCanMatchsUser() {
        val netflix = Platform.Netflix
        val netflixUserId ="juan@example.com"
        val nickname = "Juan"
        val location = "Bs As, Argentina"
        val user1 = UserReview(netflix,netflixUserId,nickname, location, "Spanish", emptyList())

        val userDoesntMatch = UserReview()
        val userMatchs = UserReview(netflix,netflixUserId,nickname, location, "Spanish", emptyList())

        Assertions.assertTrue(user1.match(userMatchs))
        Assertions.assertFalse(user1.match(userDoesntMatch))
    }

    @Test
    fun userMakesReviewTest() {
        val netflix = Platform.Netflix
        val netflixUserId ="juan@example.com"
        val nickname = "Juan"
        val location = "Bs As, Argentina"
        var user1 = UserReview(netflix,netflixUserId,"Juan", location, "Spanish", emptyList())
        var rev1 = Review(Title.createAnyTitle(), "Excellent", "Some good description", 5, false, user1,nickname)

        user1.addReview(rev1)

        Assertions.assertEquals(1,user1.reviews?.size)
    }
}