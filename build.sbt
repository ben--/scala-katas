name := "primes"

version := "1.0"

scalaVersion := "2.11.8"

testOptions in Test += Tests.Argument("-oDS")

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.12.0"
