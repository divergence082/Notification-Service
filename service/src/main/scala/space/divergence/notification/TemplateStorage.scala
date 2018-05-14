package space.divergence.notification

import scala.concurrent.Future


trait TemplateStorage[Data, Message] extends TemplateRegistry[Data, Message] {
  def save(template: Template[Data, Message]): Future[Template[Data, Message]]
  def delete(id: String): Future[Unit]
}