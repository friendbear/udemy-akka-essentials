name := "udemy-akka-essentials"

version := "0.1"

scalaVersion := "2.12.7"


val akkaVersion = "2.5.13"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.2.9"
)
