package common

import java.util.Properties

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Try,Success,Failure}

object Constants{

  //logger.info("Initializing the property file")

  private val fileName = "C:\\Users\\sunny\\IdeaProjects\\mongo-data-pipeline\\src\\main\\resources\\config.properties"
  private val properties = new Properties()

  def props = properties.load(getClass.getResourceAsStream(fileName))

  //val CONNECTIONSTRING:String = props.get("local.connection").toString
  //val DB:String = properties.get("local.defaultDB").toString
  val LOCALHOST:String = "mongodb://localhost"
  val DB:String = "analytics"
  val PORT = 27017



}
