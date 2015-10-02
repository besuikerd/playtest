package models

import com.besuikerd.playtest.dal.{DALComponent, DriverComponent}

case class User(
  username: String,
  email:String,
  digest:String,
  id: Option[Int] = None
)

trait UserComponent extends DALComponent {

  import driver.api._

  class Users(tag: Tag) extends Table[User](tag, "user") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def email = column[String]("email")
    def digest = column[String]("digest")

    def userIndex = index("user_index", username, unique = true)

    def * = (username, email, digest, id.?) <> ((User.apply _).tupled, User.unapply)
  }

  val users = TableQuery[Users]
}
