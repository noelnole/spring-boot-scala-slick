package poc.configuration

import slick.driver.MySQLDriver.api._
import scala.concurrent.duration._


/**
  * Database configuration
  *
  * @author Noel Rodriguez
  */

object DataBase {

   val db = Database.forConfig("h2mem1")
  // val  db = Database.forURL("jdbc:h2:mem:slick", driver = "org.h2.Driver")


}


