name := """akkademy-db-client-scala"""

version := "1.0"

scalaVersion := "2.12.6"


// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.akkademy-db"   %% "akkademy-db-scala" % "0.0.1-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

