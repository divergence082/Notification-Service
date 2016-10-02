package space.divergence.notification.service

import space.divergence.notification.TemplateProperties

import scala.concurrent.Future


trait TemplateService {
  def registerTemplate(template: String): Future[String]
  def unregisterTemplate(id: String): Future[Unit]
  def renderTemplate(id: String, properties: TemplateProperties): Future[String]
}
