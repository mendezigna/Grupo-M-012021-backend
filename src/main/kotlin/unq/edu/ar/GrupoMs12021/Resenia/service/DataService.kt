package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Platform
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Review
import unq.edu.ar.GrupoMs12021.Resenia.model.review.UserReview
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleBasicInformation
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Actor
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Category
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Employee
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.*


@Service
class DataService(private val titleDAO: TitleDAO,
                  private val reviewDAO: ReviewDAO,
) : ApplicationRunner {

    val cast1 = Cast("Steven Spielberg", listOf(Actor("Carmencita", listOf("Carmencita"))), listOf(Employee("Jose", Category.CINEMATOGRAPHER)))
    val cast2 = Cast("Peter Jackson", listOf(Actor("Jack Nicholson", listOf("Antonio")), Actor("Al Pacino", listOf("Al Pacino"))), listOf(Employee("Jorge", Category.WRITER), Employee("Miguel", Category.PRODUCER)))
    val cast3 = Cast("Martin Scorsese", listOf(Actor("Dustin Hoffman", listOf("Jacob Calder")), Actor("Johnny Depp", listOf("Brian McKnight"))), listOf(Employee("Roberto", Category.COMPOSER)))
    val cast4 = Cast("Christopher Nolan", listOf(Actor("Tom Hanks", listOf("Burton Hardesty")), Actor("Christopher Plummer", listOf("Bang Jack"))), listOf(Employee("Luis", Category.WRITER), Employee("Melina", Category.PRODUCTION_DESIGNER)))
    val cast5 = Cast("Steven Soderbergh", listOf(Actor("Michael Caine", listOf("Chance Bowman")), Actor("Sean Penn", listOf("Azzam"))), listOf(Employee("Silvia", Category.PRODUCTION_DESIGNER), Employee("Emilio", Category.PRODUCER)))
    val cast6 = Cast("Ridley Scott", listOf(Actor("George Clooney", listOf("Willie Tanner")), Actor("Tom Wilkinson", listOf("Sabra"))), listOf(Employee("Alberto", Category.WRITER), Employee("Emiliana", Category.WRITER)))
    val cast7 = Cast("Quentin Tarantino", listOf(Actor("Denzel Washington", listOf("Fernando Hidalgo")), Actor("Ed Harris", listOf("King Zagro"))), listOf(Employee("Mauricio", Category.PRODUCER)))
    val cast8 = Cast("Michael Mann", listOf(Actor("Robert De Niro", listOf("Fred Lawrence")), Actor("Christian Bale", listOf("Junichi Shoda"))), listOf(Employee("Sofia", Category.WRITER), Employee("Jose Luis", Category.CINEMATOGRAPHER_DIRECTOR_OF_PHOTOGRAPHY)))
    val cast9 = Cast("James Cameron", listOf(Actor("Daniel Day-Lewis", listOf("Tom Bradford")), Actor("Martin Sheen", listOf("Edu Riba"))), listOf(Employee("Elizabeth", Category.CINEMATOGRAPHER), Employee("Antonella", Category.ARCHIVE_FOOTAGE)))
    val cast10 = Cast("Joel and Ethan Coen", listOf(Actor("Sidney Poitier", listOf("Pascale De Backer")), Actor("William Hurt", listOf("Danny"))), listOf(Employee("Ludmila", Category.CINEMATOGRAPHER), Employee("Aldana", Category.PRODUCER)))
    val cast11 = Cast("Guillermo del Toro", listOf(Actor("Robert Duvall", listOf("Donald Hollinger")), Actor("Tom Cruise", listOf("Salvador Cerinza", "Pedro José Donoso"))), listOf(Employee("Nicolas", Category.PRODUCTION_DESIGNER), Employee("Juan", Category.COMPOSER)))
    val cast12 = Cast("David Fincher", listOf(Actor("Gene Hackman", listOf("Ruth Bauman")), Actor("John Travolta", listOf("Cinema Snob", "Walt Right"))), listOf(Employee("Sebastian", Category.WRITER)))
    val cast13 = Cast("Tim Burton", listOf(Actor("Leonardo DiCaprio", listOf("Vittorio Barrini")), Actor("Gary Oldman", listOf("Jermaine Stewart"))), listOf(Employee("Benjamin", Category.COMPOSER), Employee("Ignacio", Category.CINEMATOGRAPHER)))
    val cast14 = Cast("Judd Apatow", listOf(Actor(" Brad Pitt", listOf("Millicent Torkelson")), Actor("Ian McKellen", listOf("Lee Chuen-Yee"))), listOf(Employee("Pablo", Category.PRODUCTION_DESIGNER)))
    val cast15 = Cast("Sam Raimi", listOf(Actor("John Lithgow", listOf("Prof. Böck")), Actor("Javier Bardem", listOf("Panellist", "Nationals Senator"))), listOf(Employee("Julieta", Category.COMPOSER)))
    val cast16 = Cast("Zack Snyder", listOf(Actor("Jon Voight", listOf("James Trivette")), Actor("Woody Harrelson", listOf("Wu", "Botsplainer", "Fear"))), listOf(Employee("Nahuel", Category.WRITER)))
    val cast17 = Cast("Darren Aronofsky", listOf(Actor("Matt Damon", listOf("Uncle Ray Firewalker")), Actor("Russell Crowe", listOf("Ben van Rooyen"))), listOf(Employee("Lucas", Category.ARCHIVE_FOOTAGE), Employee("Julian", Category.PRODUCER)))
    val cast18 = Cast("Danny Boyle", listOf(Actor("Geoffrey Rush", listOf("Alex Cahill")), Actor("Colin Firth", listOf("Dr. Carly Manning"))), listOf(Employee("Jorge", Category.PRODUCTION_DESIGNER), Employee("Miguel", Category.CINEMATOGRAPHER)))
    val cast19 = Cast("Clint Eastwood", listOf(Actor("Kenneth Branagh", listOf("Cordell Walker")), Actor("Will Smith", listOf("Isabella Toscano"))), listOf(Employee("Nacho", Category.WRITER)))
    val cast20 = Cast("Ron Howard", listOf(Actor("Jeff Bridges", listOf("Greg Mitchell")), Actor("Morgan Freeman", listOf("Bo Brady"))), listOf(Employee("Sabrina", Category.PRODUCTION_DESIGNER), Employee("Carlos", Category.COMPOSER)))
    val cast21 = Cast("Ang Lee", listOf(Actor("Robert Redford", listOf("Pedro Rodrigues")), Actor("Mark Ruffalo", listOf("Victor Kiriakis"))), listOf(Employee("Antonieta", Category.COMPOSER)))
    val cast22 = Cast("Paul Thomas Anderson", listOf(Actor("Bryan Cranston", listOf("Gustavo Bergantin")), Actor("Ralph Fiennes", listOf("Brian Scofield"))), listOf(Employee("Mengano", Category.CINEMATOGRAPHER), Employee("Maria Juana", Category.CINEMATOGRAPHER_DIRECTOR_OF_PHOTOGRAPHY)))
    val cast23 = Cast("Paul Greengrass", listOf(Actor("Ben Kinglsey", listOf("Clare Moran")), Actor("Mahershala Ali", listOf("Roman Brady"))), listOf(Employee("Laura", Category.WRITER), Employee("Milena", Category.PRODUCER)))
    val cast24 = Cast("Pedro Almodovar", listOf(Actor("Jon Hamm", listOf("Dr. Bruce Russell")), Actor("Viggo Mortensen", listOf("Franco Kelly"))), listOf(Employee("Alejandro", Category.COMPOSER), Employee("Barbara", Category.WRITER)))
    val cast25 = Cast("Jon Favreau", listOf(Actor("Tommy Lee Jones", listOf("Gajan")), Actor("Sam Rockwell", listOf("Dr. Piccard"))), listOf(Employee("Esteban", Category.CINEMATOGRAPHER_DIRECTOR_OF_PHOTOGRAPHY)))

    final val title1: Title = Title.createTitleWith("tt0000001", TitleBasicInformation(TitleType.SHORT, "Carmecita", 1894, null, 1, listOf(Genres.DOCUMENTARY, Genres.SHORT)), listOf(), cast1)
    final val title2: Title = Title.createTitleWith("tt0000002", TitleBasicInformation(TitleType.MOVIE, "American Beer", 2004, null, 105, listOf(Genres.DOCUMENTARY)), listOf(), cast2)
    final val title3: Title = Title.createTitleWith("tt0000003", TitleBasicInformation(TitleType.TVSERIES, "All About You", 1874, null, 15, listOf(Genres.DOCUMENTARY, Genres.FAMILY)), listOf(), cast3)
    final val title4: Title = Title.createTitleWith("tt0000004", TitleBasicInformation(TitleType.MOVIE, "The Fast Express", 1942, null, 22, listOf(Genres.ACTION, Genres.DRAMA, Genres.THRILLER)), listOf(), cast4)
    val title5: Title = Title.createTitleWith("tt0000005", TitleBasicInformation(TitleType.TVEPISODE, "How to Be Happy Though Married", 1965, null, 22, listOf(Genres.COMEDY)), listOf(), cast5)
    val title6: Title = Title.createTitleWith("tt0000006", TitleBasicInformation(TitleType.SHORT, "Santa for Hire", 2020, null, 10, listOf(Genres.COMEDY, Genres.SHORT, Genres.THRILLER)), listOf(), cast6)
    val title7: Title = Title.createTitleWith("tt0000007", TitleBasicInformation(TitleType.MOVIE, "Anjana", 2010, null, 79, listOf(Genres.MUSIC, Genres.ROMANCE)), listOf(), cast7)
    val title8: Title = Title.createTitleWith("tt0000008", TitleBasicInformation(TitleType.TVSERIES, "Miracle Workers", 2019, null, 22, listOf(Genres.COMEDY, Genres.DRAMA)), listOf(), cast8)
    val title9: Title = Title.createTitleWith("tt0000009", TitleBasicInformation(TitleType.TVSERIES, "All stars: De serie", 1999, 2001, 30, listOf(Genres.COMEDY, Genres.DRAMA, Genres.SPORT)), listOf(), cast9)
    val title10: Title = Title.createTitleWith("tt0000010", TitleBasicInformation(TitleType.TVMOVIE, "The American", 1998, null, 90, listOf(Genres.DRAMA)), listOf(), cast10)
    val title11: Title = Title.createTitleWith("tt0000011", TitleBasicInformation(TitleType.VIDEO, "Max Magician and the Legend of the Rings", 2002, null, 86, listOf(Genres.COMEDY, Genres.FAMILY, Genres.ADVENTURE)), listOf(), cast11)
    val title12: Title = Title.createTitleWith("tt0000012", TitleBasicInformation(TitleType.SHORT, "Max", 2002, null, 19, listOf(Genres.DRAMA, Genres.SHORT)), listOf(), cast12)
    val title13: Title = Title.createTitleWith("tt0000013", TitleBasicInformation(TitleType.MOVIE, "My Friend Su", 2001, null, 58, listOf(Genres.DOCUMENTARY)), listOf(), cast13)
    val title14: Title = Title.createTitleWith("tt0000014", TitleBasicInformation(TitleType.TVSERIES, "Platinum", 2003, 2003, 60, listOf(Genres.CRIME, Genres.DRAMA, Genres.MUSIC)), listOf(), cast14)
    val title15: Title = Title.createTitleWith("tt0000015", TitleBasicInformation(TitleType.TVSERIES, "Rod, Jane and Freddy", 1981, 1991, 0, listOf(Genres.FAMILY, Genres.MUSIC)), listOf(), cast15)
    val title16: Title = Title.createTitleWith("tt0000016", TitleBasicInformation(TitleType.TVSERIES, "Punk'd", 2003, 2015, 30, listOf(Genres.COMEDY)), listOf(), cast16)
    val title17: Title = Title.createTitleWith("tt0000017", TitleBasicInformation(TitleType.SHORT, "Covered Girls", 2002, null, 20, listOf(Genres.DOCUMENTARY, Genres.SHORT)), listOf(), cast17)
    val title18: Title = Title.createTitleWith("tt0000018", TitleBasicInformation(TitleType.MOVIE, "Cuatro piernas", 2002, null, 82, listOf(Genres.DRAMA)), listOf(), cast18)
    val title19: Title = Title.createTitleWith("tt0000019", TitleBasicInformation(TitleType.SHORT, "Explodium", 2000, null, 3, listOf(Genres.ANIMATION, Genres.COMEDY)), listOf(), cast19)
    val title20: Title = Title.createTitleWith("tt0000020", TitleBasicInformation(TitleType.TVSERIES, "Happy Memories of the Ma's", 2010, 2011, 0, listOf(Genres.DRAMA)), listOf(), cast20)
    val title21: Title = Title.createTitleWith("tt0000021", TitleBasicInformation(TitleType.TVEPISODE, "Breaking Free: Rahaf's Escape to Canada", 2019, null, 0, listOf(Genres.DOCUMENTARY)), listOf(), cast21)
    val title22: Title = Title.createTitleWith("tt0000022", TitleBasicInformation(TitleType.VIDEO, "Jack's Halloween JamBOOree 2", 2019, null, 0, listOf()), listOf(), cast22)
    val title23: Title = Title.createTitleWith("tt0000023", TitleBasicInformation(TitleType.SHORT, "Lost and Found", 2017, null, 5, listOf(Genres.SHORT)), listOf(), cast23)
    val title24: Title = Title.createTitleWith("tt0000024", TitleBasicInformation(TitleType.TVSERIES, "Diego al 100%", 1984, 1985, 0, listOf(Genres.COMEDY)), listOf(), cast24)
    val title25: Title = Title.createTitleWith("tt0000025", TitleBasicInformation(TitleType.TVSERIES, "The Comedy Game", 1971, 1973, 30, listOf(Genres.COMEDY)), listOf(), cast25)

    final var user1: UserReview = UserReview(Platform.Netflix,"juan_yoPago@example.com","Juan","Buenos Aires, Argentina","spanish")
    final var user2: UserReview = UserReview(Platform.AmazonPrime,"juan_yoPago@example.com","Juan","Buenos Aires, Argentina","spanish")
    final var user3: UserReview = UserReview(Platform.DisneyPlus,"juan_yoPago@example.com","Juan Jr","Buenos Aires, Argentina","spanish")
    final var user4: UserReview = UserReview(Platform.HBOPlus,"juan_yoPago@example.com","Juancito","Buenos Aires, Argentina","spanish")
    final var user5: UserReview = UserReview(Platform.Netflix,"pablo.campeon@example.com","Pablito","Salta, Argentina","spanish")


    var rev1: Review = Review(title1, "Excellent", "Some good description", 5, false, user1,"Juan")
    var rev2: Review = Review(title2, "The worst", "Some bad description", 1, false, user1,"Juan")
    var rev3: Review = Review(title3, "Okay...", "Some ok description", 3, false, user2,"Juan")
    var rev4: Review = Review(title4, "Good", "Some good description", 4, false, user3,"Juan Jr")
    var rev5: Review = Review(title4, "Good", "Some goood description", 3, false, user4,"Juancito")

    fun setUp(){
        user1.addReview(rev1)
        user1.addReview(rev2)
        user2.addReview(rev3)
        user3.addReview(rev4)
        user4.addReview(rev5)
    }

    override fun run(args: ApplicationArguments?) {
        setUp()
        val titles = listOf(title1,title2, title3,title4,title5,title6,title7,title8,title9,title10,title11,title12,title13,title14,title15,title16,title17,title18,title19,title20,title21,title22,title23,title24,title25)
        titleDAO.saveAll(titles)
        reviewDAO.saveAll(listOf(rev1, rev2, rev3, rev4, rev5))

    }
}