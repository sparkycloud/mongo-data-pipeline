import common.MongoConnectionBuilder._
import common.Helpers._
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.collection.immutable.Document
import weekOne.Transformation
object Executor extends App {

  implicit val movieCollection:MongoCollection[Document] = getDatabase.getCollection("movies_initial")

  Transformation.executePipeline.printResults()

  Transformation.getPipelineWithAggregate.printResults()

  Transformation.getTopTenIMDBMovies.printResults()

  closeConnection


}
