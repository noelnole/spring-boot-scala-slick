name := "devstack-poc-slick"

version := "1.0"

scalaVersion := "2.11.8"

sbtVersion := "0.13.8"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % "1.3.2.RELEASE",
  "org.springframework.boot" % "spring-boot-starter-test" % "1.3.2.RELEASE",
  "org.springframework.boot" % "spring-boot-starter" % "1.3.2.RELEASE",
  "org.projectlombok" % "lombok" % "1.16.10",
  "com.typesafe.slick" %% "slick" % "3.0.3",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.0",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "com.h2database" % "h2" % "1.4.187",
  "com.wordnik" % "swagger-core" % "1.5.3-M1",
  "io.springfox" % "springfox-swagger2" % "2.0.1",
  "io.springfox" % "springfox-swagger-ui" % "2.0.2",
 "com.fasterxml" % "jackson-module-scala" % "1.9.3"





)