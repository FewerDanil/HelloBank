import play.Project._

name := "hello-bank"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  "org.hibernate" % "hibernate-core" % "5.1.0.Final",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "commons-validator" % "commons-validator" % "1.5.0"
)

playJavaSettings
