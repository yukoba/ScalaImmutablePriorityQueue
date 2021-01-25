name := "ImmutablePriorityQueue"
version := "0.1.5"
scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.2.3" % Test,
  "org.scalaz" %% "scalaz-core" % "7.2.28"
)
