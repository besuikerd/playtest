package forms

import play.api.data._
import play.api.data.Forms._

case class LoginForm(username:String, password:String)
object LoginForm{
  val form = Form(mapping(
    "username" -> text,
    "password" -> text
  )(LoginForm.apply)(LoginForm.unapply))
}