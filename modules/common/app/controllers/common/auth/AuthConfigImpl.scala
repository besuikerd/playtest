package controllers.common.auth

import jp.t2v.lab.play2.auth._
import models._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.{Future, ExecutionContext}
import scala.reflect._

trait AuthConfigImpl extends AuthConfig{

  val dal:CommonDAL

  import dal.driver.api._

  override type Id = String
  override type User = models.User
  override type Authority = String

  val idTag: ClassTag[Id] = classTag[Id]
  val sessionTimeoutInSeconds = 3600

  override def resolveUser(id: Id)(implicit ctx: ExecutionContext): Future[Option[User]] = {
    val query = dal.users.filter(_.username === id).result.headOption
    dal.db.run(query)
  }

  override def loginSucceeded(request: RequestHeader)(implicit ctx:ExecutionContext):Future[Result] = {
    Future.successful(Redirect(controllers.common.routes.Application.index()))
  }

  override def logoutSucceeded(request: RequestHeader)(implicit ctx:ExecutionContext):Future[Result] = {
    Future.successful(Redirect(controllers.common.routes.Application.index()))
  }

  override def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(controllers.common.routes.Application.index())
  )

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Forbidden("no permission"))
  }

  override def authorize(user:User, authority:Authority)(implicit ctx: ExecutionContext): Future[Boolean] = {
    Future.successful(true)
  }
}
