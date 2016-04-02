import play.Project._

name := "hello-bank"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJpa,
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  "org.hibernate" % "hibernate-core" % "4.3.10.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final"
)

playJavaSettings
