package poc.service

import org.springframework.stereotype.Service
import poc.configuration.DataBase
import poc.model.{Car, Cars, Person}
import slick.lifted.TableQuery

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import slick.driver.MySQLDriver.api._


/**
  * This class implements the car service, which has thº1e next list of methods:
  * <ul>
  *   <li>selectCarByModel</li>
  *   <li>selectAllCars</li>
  *   <li>insertCar</li>
  *   <li>deleteByName</li>
  *   <li>carTransaction</li>
  * </ul>
  *
  * @author Noel Rodriguez
  */

@Service(value = "carsService")
class CarsService {

  val db = DataBase.db;

  val timeout = 15.seconds

  val cars = TableQuery[Cars]

  /**
    * Get a car by it model
    *
    * @param model model car
    * @return Car
    */
  def selectCarByModel (model: String) : Option[Car] = {
    val query = cars.filter(car => car.model === model)

    return Await.result(
      db.run(query.result).map(_.headOption) , timeout  )
  }

  /**
    * Get the all the cars
    * @return MutableList[Car]
    */
  def selectAllCars (): scala.collection.mutable.MutableList[Car] = {
    val car = scala.collection.mutable.MutableList[Car]()
    val query = cars
    Await.result(
      db.run(query.result).map  { _ foreach (car += _) }, timeout
    )
    return car
  }

  /**
    * Insert a car by sqlu
    */
  def insertCar(): Unit = {
    val q = sqlu"insert into cars(model,color) values ('renault','violet')"
    Await.result(
      db.run(q).map{result =>}, timeout)
  }

  /**
    * Delete a person by name
    *
    * @param model
    * @return number of elements deleted
    */
  def deleteByName(model: String) : Integer = {
    val q = cars.filter(_.model === model ).delete
    var   numElementsDeleted : Integer = 0
    Await.result(
      db.run(q).map { numAffectedRows =>
       numElementsDeleted = numAffectedRows
      }, timeout)

    return numElementsDeleted
  }

  /**This method will delete a car and create another one car but in this moment i will abort the transaction
    * and generate again the deleted car, in case of the fail add one car
    */
  def carTransaction ( ):Unit  = {


  }

  /**
    * Insert a car in the database with DBIO
    *
    * @return car
    */
  def saveCarWithDBIO(car : Car) : Car = {
    val insert = DBIO.seq(cars +=car)
    val createDatabase = DBIO.seq(insert)
    return car
  }

  /**
    * Delete car table
    */
  def dropCar (): Unit = {
    val dropSchema = cars.schema.drop
    DBIO.seq(dropSchema)
  }


}
