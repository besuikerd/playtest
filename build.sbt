name := """play-modular-multiproject"""

lazy val core = (project in file("."))
  .enablePlugins(PlayScala)
  .dependsOn(common).aggregate(common)

lazy val common: Project = (project in file("modules/common"))
  .enablePlugins(PlayScala)
  .dependsOn(shared).aggregate(shared)
  .settings(
    aggregateReverseRoutes := Seq()
  )

lazy val shared = (project in file("shared"))
  .enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)


sbtPlugin := true

routesGenerator := InjectedRoutesGenerator

initialCommands in console :=
  """
     def start = com.besuikerd.playtest.console.SbtConsoleUtils.startApplication()
     def stop = com.besuikerd.playtest.console.SbtConsoleUtils.stopApplication()
     import models._
     val dal = new CommonDAL
     import dal._
     import dal.driver.api._
     import scala.concurrent.ExecutionContext.Implicits.global
  """.stripMargin