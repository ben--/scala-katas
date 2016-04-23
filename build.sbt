name := "primes"

version := "1.0"

scalaVersion := "2.11.7"

testOptions in Test += Tests.Argument("-oDS")

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "joda-time" % "joda-time" % "2.9.3"
