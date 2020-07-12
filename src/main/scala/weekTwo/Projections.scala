package weekTwo

import common.Transformation
import org.mongodb.scala.AggregateObservable
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Aggregates._

object Projections extends Transformation {


  def projection(collectionName: String,pipeline:Seq[Bson]):AggregateObservable[Document] = {
    executePipeline(pipeline, collectionName)
  }

  val pipeline1 = Seq(
    limit(100),
    project(Document(
      """
        |{
        |            'title': 1,
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
        |        }
        |    }""".stripMargin)),
    out("movies_scratch")
  )
  val pipeline_date_cast = Seq(
    limit(100),
    project(Document("""
       |{'title': 1,
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
       |            'released': {
       |                '$cond': {
       |                    'if': {'$ne': ["$released", ""]},
       |                    'then': {
       |                        '$dateFromString': {
       |                            'dateString': "$released"
       |                        }
       |                    },
       |                    'else': ""}},
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
       |        }
       |    }""".stripMargin)),
    out("movies_scratch2")
   )

  val add_field_pipeline = Seq(
    limit(100),
    addFields(Document(""""""))
  )

}