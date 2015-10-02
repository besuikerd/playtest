package controllers.common

import javax.inject.Inject
import models.{User, CommonDAL}
import play.api.mvc._
import scala.concurrent._
import scala.concurrent.duration._

class Application extends Controller{

  def index = Action{ implicit req =>
    Ok("index")
  }
}
