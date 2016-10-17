package poc.model

import scala.beans.BeanProperty


/**
  * Car class
  *
  * @author Noel Rodriguez
  */
case class Car (id: Option[Long] = None,@BeanProperty model: String , @BeanProperty color: String) extends BaseModel


