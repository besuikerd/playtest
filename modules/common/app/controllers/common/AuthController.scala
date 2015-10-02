package controllers.common

import com.google.inject.Inject
import controllers.common.auth.AuthConfigImpl
import forms.LoginForm
import jp.t2v.lab.play2.auth.{AuthElement, LoginLogout}
import models.CommonDAL
import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class AuthController @Inject()(override val dal: CommonDAL, val messagesApi: MessagesApi) extends Controller
  with LoginLogout
  with AuthConfigImpl
  with I18nSupport
{


  def login = Action{ implicit request =>
    Ok(views.html.auth.login(LoginForm.form))
  }

  def logout = Action{
    Ok("logout")
  }

  def authenticate = Action.async{ implicit request =>
    LoginForm.form.bindFromRequest.fold(
      errorForm => Future.successful(BadRequest(views.html.auth.login(errorForm))),
      user => gotoLoginSucceeded(user.username)
    )
  }
}
