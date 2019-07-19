name := "ImmutablePriorityQueue"
version := "0.1.5"
scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.28"
)
