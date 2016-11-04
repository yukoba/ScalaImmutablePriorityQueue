name := "ImmutablePriorityQueue"
version := "0.1.4"
scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.7"
)
