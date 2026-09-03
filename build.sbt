ThisBuild / version := "0.1.14"
ThisBuild / scalaVersion := "3.9.0"

lazy val root = (project in file("."))
    .settings(
        name := "ImmutablePriorityQueue",
        libraryDependencies ++= Seq(
            "org.scalaz" %% "scalaz-core" % "7.3.9",
            "org.scalatest" %% "scalatest" % "3.2.20" % Test,
        ),
    )
