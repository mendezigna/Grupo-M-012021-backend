package unq.edu.ar.GrupoMs12021.Resenia.model.client

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MetricsTest {

    @Test
    fun addReviewTest(){
        val metrics = Metrics()
        assertEquals(metrics.reviews, 0)
        metrics.addReview()
        assertEquals(metrics.reviews, 1)
    }

    @Test
    fun addReportTest(){
        val metrics = Metrics()
        assertEquals(metrics.reports, 0)
        metrics.addReport()
        assertEquals(metrics.reports, 1)
    }

    @Test
    fun addLikeTest(){
        val metrics = Metrics()
        assertEquals(metrics.likes, 0)
        metrics.addLike(true)
        assertEquals(metrics.likes, 1)
    }

    @Test
    fun addDislikeTest(){
        val metrics = Metrics()
        assertEquals(metrics.dislikes, 0)
        metrics.addLike(false)
        assertEquals(metrics.dislikes, 1)
    }
}