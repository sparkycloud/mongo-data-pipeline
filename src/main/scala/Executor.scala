import common.MongoConnectionBuilder._
import weekOne.Transformation
object Executor extends App {

  val movieCollection = getDatabase.getCollection("movies_initial")

  Transformation.projectionOne(movieCollection)

  closeConnection


}
