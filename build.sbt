enablePlugins(org.nlogo.build.NetLogoExtension)

name := "gs"
version := "1.1.1"
isSnapshot := true


javaSource in Compile := baseDirectory.value / "src" / "org" / "graphstream" / "netlogo" / "extension"
javacOptions ++= Seq("-g", "-deprecation", "-Xlint:all", "-Xlint:-serial", "-Xlint:-path", "-encoding", "us-ascii", "--release", "11")

//scalaVersion := "3.2.0"
//scalaSource in Test := baseDirectory.value / "src" / "test"
//scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings", "-feature", "-encoding", "us-ascii", "-release", "11")

netLogoVersion := "6.3.0"
netLogoClassManager := "org.graphstream.netlogo.extension.GSManager"
resolvers += "netlogo" at "https://dl.cloudsmith.io/public/netlogo/netlogo/maven/"


libraryDependencies ++= Seq(
    "org.nlogo"          % "netlogo"        % "6.3.0",
  "org.graphstream"  % "gs-core" % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-core-2.0.jar",
  "org.graphstream"  % "gs-algo" % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-algo-2.0.jar",
  "org.graphstream"    % "gs-ui-swing"   % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-ui-swing-2.0.jar",
)