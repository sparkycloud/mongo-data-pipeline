package weekOne
import common.MongoConnectionBuilder._
import org.mongodb.scala.{Document, MongoCollection, Observable}
import common.Helpers._
import org.mongodb.scala.model.Aggregates._
object Transformation {

  val movieCollection = getCollection("movies_initial")

  def projectionOne(collection:MongoCollection[Document]):Unit = {

    val pipeline = Seq(limit(100),
      project(Document(
        """{
          |'title': 1,
          |            'year': 1,
          |            'directors': {'$split': ["$director", ", "]},
          |            'actors': {'$split': ["$cast", ", "]},
          |            'writers': {'$split': ["$writer", ", "]},
          |            'genres': {'$split': ["$genre", ", "]},
          |            'languages': {'$split': ["$language", ", "]},
          |            'countries': {'$split': ["$country", ", "]},
          |            'plot': 1,
          |            'fullPlot': "$fullplot",
          |            'rated': "$rating",
          |            'released': 1,
          |            'runtime': 1,
          |            'poster': 1,
          |            'imdb': {
          |                'id': "$imdbID",
          |                'rating': "$imdbRating",
          |                'votes': "$imdbVotes"
          |                },
          |            'metacritic': 1,
          |            'awards': 1,
          |            'type': 1,
          |            'lastUpdated': "$lastupdated"
          |
          |}""".stripMargin))

    )

    collection.aggregate(pipeline).printHeadResult()

  }



}
