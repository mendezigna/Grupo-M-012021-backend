package unq.edu.ar.GrupoMs12021.Resenia.model.review

import org.junit.Assert
import org.junit.jupiter.api.Test

class ReportUnitTest {

    @Test
    fun aReportHasReasonReviewandUser(){
        val review = PublicReview()
        val reason = "A text explaning a reason to report the review"
        val ID: Long = 1L;
        var report = Report(review,reason)
        report.id = ID

        Assert.assertEquals(review, report.review)
        Assert.assertEquals(reason, report.reason)
        Assert.assertEquals(ID, report.id)

    }
}