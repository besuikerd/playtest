name := "common"
scalaVersion := "2.11.6"
routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  "jp.t2v" %% "play2-auth" % "0.14.1"
)
