package unq.edu.ar.GrupoMs12021.Resenia.service.filter

/* Constants for Titles filter */
object TitleFields {
    val numPage = "numPage"
    val sizePage = "sizePage"
    val titleType = "titleType"
    val genre = "genre"
    val titleName = "titleName"
    val director = "director"
    val actor = "actor"
    val decade = "decade" // Start year between {decade} {decade+10}
    val rating  = "rating"
    val likesGT   = "likesGT" // Likes greaterThan {int}
}

/* Constants for Reviews filter */
object FiltersReviewFields {

    val titleID = "titleId"
    val numPage = "numPage"
    val sizePage = "sizePage"
    val sort = "sort"
    val order = "order"
    val hasNoReports = "NoReports"
    val reviewType = "reviewType"
    val rating = "rating"
    val language = "language"
    val location = "origin"
    val platform = "platform"
    val spoiler = "spoiler"
    val date = "date"
}