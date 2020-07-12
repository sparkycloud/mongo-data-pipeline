import common.MongoConnectionBuilder._
import common.Helpers._
import common.Constants
import weekTwo.Projections._
object Executor extends App  {
 projection("movies_initial",pipeline1)
 projection("movies_initial",pipeline_date_cast)

  closeConnection


}
