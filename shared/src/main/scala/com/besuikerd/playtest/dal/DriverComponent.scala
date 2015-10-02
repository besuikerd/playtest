package com.besuikerd.playtest.dal

import play.api.db.slick.DatabaseConfigProvider
import play.api.Play.current
import slick.driver.JdbcProfile

trait DriverComponent {
  lazy val config = DatabaseConfigProvider.get[JdbcProfile]
  lazy val driver = config.driver
  lazy val db = config.db
}