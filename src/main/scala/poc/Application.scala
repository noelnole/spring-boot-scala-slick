package poc

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import poc.configuration.DataBase
import poc.model.{Cars, People, Person}
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import slick.driver.MySQLDriver.api._
import slick.jdbc.meta.MTable
import scala.concurrent.duration._
import slick.lifted.TableQuery

/**
  * Spring boot application with scala and slick
  *
  * @author Noel Rodriguez
  */

@SpringBootApplication
@Slf4j
class Application

  object Application {

    val timeout = 15.seconds

    val people = TableQuery[People]

    val cars = TableQuery[Cars]

    val db = DataBase.db;



    def main(args: Array[String]) : Unit = {
      //insertAndCreatePersonDBIO
      Await.result(db.run(people.schema.create), Duration.Inf)
      Await.result(db.run(cars.schema.create), Duration.Inf)
      insertCars()
      insertPeople()
      SpringApplication.run(classOf[Application], args:_ *)
    }

    /**
      * Insert a car with a sqlu query
      */
    def insertCars(): Unit = {
      // uso sqlu porque no devuelvo un resultset
      val q = sqlu"insert into cars(model,color) values ('mazda','red')"

      Await.result(
        db.run(q).map { res =>
          println(res)
        }, timeout)

    }

    /**
      * Insert a people with a sqlu query
      */
    def insertPeople(): Unit = {
      val q = sqlu"insert into people(name,email,password) values ('pepito','pepito@pepito.com','red')"

      Await.result(
        db.run(q).map { res =>
          println(res)
        }, timeout)
    }

    /**
      * Insert and create with DBIO, so non blocking io
      */
    def insertAndCreatePersonDBIO (): Unit = {
      val person = Person (email = "hola@hola.es", name="pepuioto", password = "password")
      val insertPerson = DBIO.seq(people +=person)
      val createSchemaAction = (people.schema).create
      val createDatabase = DBIO.seq(createSchemaAction, insertPerson)
    }

  }
