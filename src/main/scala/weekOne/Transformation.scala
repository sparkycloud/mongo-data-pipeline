package weekOne


import common.MongoConnectionBuilder._
import org.mongodb.scala.{AggregateObservable, Document, MongoCollection, Observable}
import org.mongodb.scala.model.{Sorts,Filters}
import org.mongodb.scala.model.Aggregates._
import common.Helpers._
object Transformation {

  val movieCollection: MongoCollection[Document] = getCollection("movies_initial")

  def executePipeline(implicit collection:MongoCollection[Document]):AggregateObservable[Document] = {

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
          |}""".stripMargin)),

      out("movies_scratch")

    )

    collection.aggregate(pipeline)

  }

  def getPipelineWithAggregate(implicit collection: MongoCollection[Document]): AggregateObservable[Document] = {

    val pipeline = Seq(
      limit(100),
      project(Document(
        """{
          |'title':1
          |}""".stripMargin))
    )
    collection.aggregate(pipeline)
  }

  def getTopTenIMDBMovies(implicit collection: MongoCollection[Document]):Observable[Document] = {
    collection
      .find()
      .filter(Filters.equal("rated", "9.1"))
      .projection(Document("{'title':1}")).sort(Sorts.descending("rated")).limit(10)
  }

}
