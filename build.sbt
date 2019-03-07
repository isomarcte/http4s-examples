import ReleaseTransformations._

// Constants //

val projectName = "http4s-examples"
val scala212    = "2.12.8"

// Groups //

val http4sG = "org.http4s"

// Artifacts //

val http4sBlazeServerA = "http4s-blaze-server"
val http4sDslA         = "http4s-dsl"

// Versions //

val http4sV = "0.18.22"

// GAVs //

lazy val http4sBlazeServer = http4sG %% http4sBlazeServerA % http4sV
lazy val http4sDsl         = http4sG %% http4sDslA % http4sV

// THISBuild Scoped Settings //

ThisBuild / organization       := "io.isomarcte"
ThisBuild / scalaVersion       := scala212

// Root Project //

lazy val root = (project in file(".")).settings(
  name := projectName,
  skip in publish := true
).aggregate(dsl)

// Projects //

lazy val dsl = project.settings(
  name := s"$projectName-dsl",
  libraryDependencies ++= Seq(
    http4sBlazeServer,
    http4sDsl
  )
)
