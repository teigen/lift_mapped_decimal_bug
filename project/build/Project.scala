import sbt._

class Project(info:ProjectInfo) extends DefaultWebProject(info){
  val LiftModule = ModuleConfiguration("net.liftweb", ScalaToolsSnapshots)
  val mapper = "net.liftweb" %% "lift-mapper" % "2.3-SNAPSHOT"
  val h2 = "com.h2database" % "h2" % "1.2.138"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.22" % "test"
}