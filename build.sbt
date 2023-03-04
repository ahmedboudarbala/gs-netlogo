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
resolvers += "jogl-all" at "file:///home/.../Desktop/NetLogo 6.3.0/lib/app/jogl-all.jar"
resolvers += "gluegen-rt" at "file:///home/.../Desktop/NetLogo 6.3.0/lib/app/gluegen-rt.jar"



libraryDependencies ++= Seq(
    "org.nlogo"          % "netlogo"        % "6.3.0",
  "org.graphstream"  % "gs-core" % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-core-2.0.jar",
  "org.graphstream"  % "gs-algo" % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-algo-2.0.jar",
  "org.graphstream"    % "gs-ui-swing"   % "2.0" from "file:///home/ns3netlogo/Desktop/graphstream/Release_2.0/gs-ui-swing-2.0.jar",
  "org.jogamp.gluegen" % "gluegen-rt" % "2.4.0" from "file:///home/.../Desktop/NetLogo/lib/app/gluegen-rt.jar", 
  "org.jogamp.jogl" % "jogl-all" % "2.4.0" from "file:///home/.../Desktop/NetLogo/lib/app/jogl-all.jar",

)
