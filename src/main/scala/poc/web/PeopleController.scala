package poc.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import poc.model.{Car, Person}
import poc.service.PeopleService

/**
  * People controller return information from the services with basic operations.
  *
  * @author Noel Rodriguez
  */

@RestController
class PeopleController @Autowired() (private val peopleService : PeopleService){


  /**
    * Get a people list
    * @return ResponseEntity
    */
  @RequestMapping(value = Array("/people"),method = Array(RequestMethod.GET))
  def people(): ResponseEntity[Any] = {
    var people = peopleService.selectAllPeople()
    return new ResponseEntity[Any](people.toString(), HttpStatus.OK)

  }


  /**
    * Return a person filter by name
    *
    * @param name person name
    * @return ResponseEntity
    */
  @RequestMapping(value = Array("/person"),method = Array(RequestMethod.GET))
  def peopleByName(@RequestParam("name") name : String): ResponseEntity[Person] = {
    val person = peopleService.selectPersonByName(name);
    return new ResponseEntity[Person](person.get, HttpStatus.OK)
  }

  /**
    * Insert a new person  with values by parameter
    *
    * @param name user name
    * @param password password
    * @param email person email
    * @return HttpStatus created
    */
  @RequestMapping(method = Array(RequestMethod.POST))
  def savePerson(@RequestParam name : String,  @RequestParam password : String, @RequestParam email : String) : ResponseEntity[Unit] = {
    val person = Person (password = password, name=name, email = email)
    peopleService.savePersonWithDBIO(person)
    return new ResponseEntity[Unit](HttpStatus.CREATED)
  }


  /**
    * Delete Person
    *
    * @param name person name
    * @return Person
    */
  @RequestMapping(method = Array(RequestMethod.DELETE))
  def deletePerson(@RequestParam ("name") name: String): ResponseEntity[Unit] = {
    peopleService.deleteByName(name)
    return new ResponseEntity[Unit](HttpStatus.OK)
  }

}
