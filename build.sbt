name := "ImmutablePriorityQueue"
version := "0.1.1"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.1"
)
