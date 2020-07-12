package common

import common.MongoConnectionBuilder.getCollection
import org.bson.conversions.Bson
import org.mongodb.scala.{AggregateObservable, Document}

trait Transformation  {

  def executePipeline(pSeq:Seq[Bson],collectionName:String):AggregateObservable[Document] = {

    getCollection(collectionName).aggregate(pSeq)
  }
}
