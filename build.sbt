
lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra := (
    <url>https://github.com/divergence082/Notification-Service</url>
      <licenses>
        <license>
          <name>BSD-style</name>
          <url>http://www.opensource.org/licenses/bsd-license.php</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:divergence082/Notification-Service.git</url>
        <connection>scm:git:git@github.com:divergence082/Notification-Service.git</connection>
      </scm>
      <developers>
        <developer>
          <id>divergence082</id>
          <name>Valeria Kononenko</name>
          <email>divergence082@gmail.com</email>
          <url>https://github.com/divergence082</url>
        </developer>
      </developers>)
)

lazy val projectSettings = Defaults.coreDefaultSettings ++
  Seq(
    organization := "space.divergence",
    scalaVersion := "2.11.8"
  )

lazy val service = Project(
  id = "service",
  base = file("service"),
  settings = projectSettings ++ publishSettings ++ Seq(
    name := "notification-service",
    version := "0.0.1",
    isSnapshot := true,
    libraryDependencies ++= Seq()
  ))
  .aggregate(template, scheduler, email, push, sms)
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )


lazy val template =
  Project(
    id = "template",
    base = file("template"),
    settings = projectSettings ++
      Seq(
        name := "notification-service-template",
        version := "0.0.1",
        isSnapshot := true,
        libraryDependencies ++= Seq(),
        publish := {},
        publishLocal := {}
      )
  )
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )


lazy val scheduler =
  Project(
    id = "scheduler",
    base = file("scheduler"),
    settings = projectSettings ++
      Seq(
        name := "notification-service-scheduler",
        version := "0.0.1",
        isSnapshot := true,
        libraryDependencies ++= Seq(),
        publish := {},
        publishLocal := {}
      )
  )
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )


lazy val email =
  Project(
    id = "email",
    base = file("email"),
    settings = projectSettings ++
      Seq(
        name := "notification-service-email",
        version := "0.0.1",
        isSnapshot := true,
        libraryDependencies ++= Seq(),
        publish := {},
        publishLocal := {}
      )
  )
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )


lazy val push =
  Project(
    id = "push",
    base = file("push"),
    settings = projectSettings ++
      Seq(
        name := "notification-service-push",
        version := "0.0.1",
        isSnapshot := true,
        libraryDependencies ++= Seq(),
        publish := {},
        publishLocal := {}
      )
  )
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )


lazy val sms =
  Project(
    id = "sms",
    base = file("sms"),
    settings = projectSettings ++
      Seq(
        name := "notification-service-sms",
        version := "0.0.1",
        isSnapshot := true,
        libraryDependencies ++= Seq(),
        publish := {},
        publishLocal := {}
      )
  )
  .settings(
    scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
    scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
  )