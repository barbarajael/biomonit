
name := "Projeto"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(evolutions,javaJdbc, cache , filters, ws, "net.sourceforge.jtds" % "jtds" % "1.3.1", "org.hibernate" % "hibernate-entitymanager" % "5.1.5.Final")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
