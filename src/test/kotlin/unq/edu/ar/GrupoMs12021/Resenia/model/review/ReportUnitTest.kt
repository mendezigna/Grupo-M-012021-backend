package unq.edu.ar.GrupoMs12021.Resenia.model.review

import org.junit.Assert
import org.junit.jupiter.api.Test

class ReportUnitTest {

    @Test
    fun aReportHasReasonReviewandUser(){
        val review = Review()
        val reason = "A text explaning a reason to report the review"
        var report = Report(review,reason)

        Assert.assertEquals(review, report.review)
        Assert.assertEquals(reason, report.reason)

    }
}