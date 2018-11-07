name := """akkademy-db"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
  "com.typesafe.akka" %% "akka-actor" % "2.5.6",
"com.typesafe.akka" %% "akka-testkit" % "2.5.6" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"

)

