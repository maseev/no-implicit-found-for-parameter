name := "no-implicit-found-for-parameter"

version := "0.1"

scalaVersion := "2.13.3"

scalacOptions ++= Seq(
  "-Xfatal-warnings",
)

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"