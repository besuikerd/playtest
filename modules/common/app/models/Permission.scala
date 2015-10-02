package models

import com.besuikerd.playtest.dal.DALComponent

case class Permission(
  path:String,
  userId: Int,
  id:Option[Int] = None
)

trait PermissionComponent { this: DALComponent with UserComponent =>
  import driver.api._



  class Permissions(tag:Tag) extends Table[Permission](tag, "permission"){

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def path = column[String]("path")
    def userId = column[Int]("user_id")
    def fkUserId = foreignKey("fk_user_id", userId, users)(_.id)
    def * = (path, userId, id.?) <> ((Permission.apply _).tupled, Permission.unapply)
  }

  val permissions = TableQuery[Permissions]
}