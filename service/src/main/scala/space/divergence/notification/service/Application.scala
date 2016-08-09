package space.divergence.notification.service

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.twitter.finagle.Http
import io.finch._
import io.finch.jackson._
import io.finch.internal.BufText

//import com.typesafe.config.ConfigFactory


object Application extends App {
//  private val _config = ConfigFactory.load()

  implicit val objectMapper: ObjectMapper = new ObjectMapper with ScalaObjectMapper {
    this.registerModule(DefaultScalaModule)
    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  }

  implicit val ee: Encode.Json[Exception] = Encode.json((e, cs) =>
    BufText(objectMapper.writeValueAsString(Map("error" -> e.getMessage)), cs)
  )

  case class Template(id: String, content: String)

  object NotificationType extends Enumeration {
    val EMAIL, PUSH, SMS = Value
  }

  case class Notification(`type`: NotificationType.Value,
                          templateId: String,
                          variables: String,
                          subscribersQuery: String)

  case class ScheduledNotification(notification: Notification, timestamp: String)

  case class Subscription(subscribersIds: List[String],
                          notification: Notification,
                          timestamp: String)

  val template: Endpoint[Template] =
    post("template" :: param("content").as[String]) {
      (content: String) => Ok(Template("id", content))
    }

  val notification: Endpoint[Subscription] =
    post("notification" :: body.as[Notification]) {
      notification: Notification =>
        Ok(Subscription(
          List.empty[String],
          notification,
          System.currentTimeMillis().toString))
    }

  val schedule: Endpoint[Subscription] =
    post("schedule" :: body.as[ScheduledNotification]) {
      scheduledNotification: ScheduledNotification =>
        Ok(Subscription(
          List.empty[String],
          scheduledNotification.notification,
          scheduledNotification.timestamp))
    }

  val endpoints = template :+: notification :+: schedule

  Http.server.serve(":8080", endpoints.toService)
}
