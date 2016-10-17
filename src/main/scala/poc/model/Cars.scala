package poc.model

import slick.driver.MySQLDriver.api._



/**
  * Car table
  *
  * @author Noel Rodriguez
  */
class Cars (tag: Tag) extends Table[Car] (tag, "CARS") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def model = column[String]("MODEL")

  def color = column[String]("COLOR")

  //Mapeo de proyecciones, proyección de los campos de la tabla
  def * = (id.?,model, color) <> (Car.tupled, Car.unapply)
}
