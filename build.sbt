ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "POCWeaver"
  )

val http4sVersion = "0.23.21"

libraryDependencies +=  "com.disneystreaming" %% "weaver-cats" % "0.8.3" % Test
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-core"         % http4sVersion,
  "org.http4s" %% "http4s-client"       % http4sVersion,
  "org.http4s" %% "http4s-server"       % http4sVersion,
)
libraryDependencies += "org.http4s" %% "http4s-blaze-client" % "0.23.14"

testFrameworks += new TestFramework("weaver.framework.CatsEffect")
