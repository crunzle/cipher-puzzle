

lazy val proj = (project in file("."))
  .enablePlugins(DockerPlugin, JavaAppPackaging)
  .settings(
    name := "puzzle",
    version := "0.1",
    scalaVersion := "2.13.1"
  )

