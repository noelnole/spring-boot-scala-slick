package poc.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._
import poc.model.{Car, Cars, Person}
import poc.service.CarsService


/**
  * Return cars information. This class have basics operation.
  *
  * @author Noel Rodriguez
  */

@RestController
@RequestMapping(value = Array("/car"))
class CarController @Autowired() (private val carsService : CarsService){


  /**
    * Get a string of cars from database
    *
    * @return responseEntity
    */
  @RequestMapping(value = Array("/cars"),method = Array(RequestMethod.GET))
  def getCars(): ResponseEntity[Any] = {
    var cars = carsService.selectAllCars()
    return new ResponseEntity[Any](cars.toString(), HttpStatus.OK)

  }

  /**
    * Get a car by model
    * @param model
    * @return ResponseEntity[Car]
    */
  @RequestMapping(method = Array(RequestMethod.GET))
  def getCarByName(@RequestParam("model") model : String): ResponseEntity[Car] = {
    val car = carsService.selectCarByModel(model);
    return new ResponseEntity[Car](car.get, HttpStatus.OK)
  }

  /**
    * Save a car into de data access
    *
    * @param model model car
    * @param color color car
    * @return a HttpStatus
    */
  @RequestMapping(method = Array(RequestMethod.POST))
  def saveCar(@RequestParam model : String,  @RequestParam color : String) : ResponseEntity[Unit] = {
    val car = Car (color = color, model = model)
    carsService.saveCarWithDBIO(car)
    return new ResponseEntity[Unit](HttpStatus.CREATED)
  }

  /**
    * Delete a cara by model
    *
    * @param model car model
    * @return ResponseEntity
    */
  @RequestMapping(method = Array(RequestMethod.DELETE))
  def deleteCar(@RequestParam ("model") model: String): ResponseEntity[Unit] = {
    carsService.deleteByName(model)
    return new ResponseEntity[Unit](HttpStatus.OK)
  }
}
