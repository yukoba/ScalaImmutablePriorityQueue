ThisBuild / version := "0.1.11"
ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "ImmutablePriorityQueue",
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.3.8",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ),
  )
