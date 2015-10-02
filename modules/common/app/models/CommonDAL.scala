package models

import com.besuikerd.playtest.dal.DriverComponent
import com.google.inject.Singleton
import slick.driver.JdbcDriver

@Singleton
class CommonDAL extends DriverComponent
  with UserComponent
  with PermissionComponent