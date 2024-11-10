ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.4"

val scalaTestV = "3.2.19"
val diffV = "0.4.3"

lazy val root = (project in file("."))
  .settings(
    name := "minimal_path",
    assembly / assemblyOutputPath := file("minimal_path.jar"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % scalaTestV % Test,
      "com.github.jatcwang" %% "difflicious-scalatest" % diffV % Test,
    )
  )

