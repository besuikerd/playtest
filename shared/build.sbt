name := "shared"
scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "1.0.1",
  "org.postgresql" % "postgresql" % "9.4-1203-jdbc42",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.3"
)

scalaSource in Compile := baseDirectory.value / "src/main/scala"