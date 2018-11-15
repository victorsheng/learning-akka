name := """akkademaid-scala"""

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.16"

lazy val akkaHttpVersion = "10.0.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" % "akka-http-core_2.12" % akkaHttpVersion,
  "com.akkademy-db"   %% "akkademy-db-scala"     % "0.0.1-SNAPSHOT",
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "org.scalatest" %% "scalatest" % "3.0.5"% "test",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)

