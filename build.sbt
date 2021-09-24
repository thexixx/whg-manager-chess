name := "chess-game-for-whg"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("net.whg.manager")

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

scalacOptions += "-deprecation"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"