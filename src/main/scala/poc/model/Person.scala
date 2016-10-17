package poc.model

import scala.beans.BeanProperty


/**
  * Person class
  *
  * @author Noel Rodriguez
  */
case class Person (id: Option[Long] = None,@BeanProperty name: String, @BeanProperty email: String,  @BeanProperty password: String) extends BaseModel




