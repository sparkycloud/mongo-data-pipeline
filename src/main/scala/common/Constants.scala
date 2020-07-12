package common

import java.util.Properties

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Try,Success,Failure}

object Constants extends LazyLogging {

  logger.info("Initializing the property file")

  private val fileName = "/config.properties"
  private val properties = new Properties()

  properties.load(getClass.getResourceAsStream(fileName))

  val CLOUDCONNECTION:String = properties.get("cloud.connection").toString
  val CLOUDDB:String = properties.get("cloud.defaultDB").toString
  val LOCALHOST:String = properties.get("local.connection").toString
  val LOCALDB:String = properties.get("local.defaultDB").toString
  val PORT = properties.get("local.port")

}
