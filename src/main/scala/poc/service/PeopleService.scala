package poc.service

import org.springframework.stereotype.Service
import poc.configuration.DataBase
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import poc.model.{Car, People, Person}
import slick.driver.MySQLDriver.api._


/**
  * People Service with methods:
  * <ul>
  *   <li>selectPersonByName</li>
  *   <li>selectAllPeople</li>
  *   <li>insertPerson</li>
  *   <li>deleteByName</li>
  * </ul>
  *
  * @author Noel Rodriguez
  */

@Service(value = "peopleService")
class PeopleService {

  val db = DataBase.db;

  val timeout = 15.seconds

  val people = TableQuery[People]

  /**
    * Return a person by name
    * @param name person name
    * @return Person
    */
  def selectPersonByName(name: String): Option[Person] = {
    val query = people.filter(person => person.name === name)

    return Await.result(
      db.run(query.result).map(_.headOption) , timeout  )
  }

  /**
    * Return people from database
    */
  def selectAllPeople(): Unit = {
    val query = people

    Await.result(
      db.run(query.result).map {
        result => println(result)
      }, timeout
    )

  }

  /**
    * Insert a person by sqlu
    */
  def insertPerson(): Unit = {
    val q = sqlu"insert into people(name,email,password) values ('pepe','pepe@gmail.com','violet')"
    Await.result(
      db.run(q).map{result =>}, timeout)
  }


  /**
    * Delete a person filter by name
    * @param name person name
    */
  def deleteByName(name: String): Unit ={
    val q = people.filter(_.name === name).delete
    var numElementsDeleted = 0
    Await.result(
      db.run(q).map {numAffectedRows => numElementsDeleted = numAffectedRows},timeout
    )
  }

  /**
    *
    * @param person
    * @return
    */
  def savePersonWithDBIO(person : Person) : Person = {
    val insert = DBIO.seq(people +=person)
    val createDatabase = DBIO.seq(insert)
    return person
  }

  /**
    * Delete person table
    */
  def dropPerson (): Unit = {
    val dropSchema = people.schema.drop
    DBIO.seq(dropSchema)
  }

}