package common

import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import scala.util.{Failure, Success, Try}

object MongoConnectionBuilder {

  def createConnection:MongoClient = {
    val client:MongoClient = Try(MongoClient(s"${Constants.LOCALHOST}")) match {
      case Success(v) => v
      case Failure(e) => println("Db not found")
        throw new Exception(e)
    }
    client
  }

  def getDatabase:MongoDatabase = {

    val db = createConnection.getDatabase(Constants.LOCALDB)
    db
  }

  def getCollection(collectionName:String):MongoCollection[Document] = {
    getDatabase.getCollection(collectionName)
  }

  def closeConnection:Unit = {
    createConnection.close()
    println("connection closed :)")
  }

}
