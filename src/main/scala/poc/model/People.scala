package poc.model

import slick.driver.MySQLDriver.api._

/**
  * This class is a table definition
  *
  * @author Noel Rodriguez
  */
class People (tag: Tag) extends Table[Person] (tag, "PEOPLE") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME")

  def email = column[String]("EMAIL")

  def password = column[String]("PASSWORD")

  //Mapeo de proyecciones, proyección de los campos de la tabla
  def * = (id.?,name, email, password) <> (Person.tupled, Person.unapply)

}
