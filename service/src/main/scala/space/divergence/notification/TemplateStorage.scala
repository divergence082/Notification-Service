package space.divergence.notification

import scala.concurrent.Future


trait TemplateStorage extends TemplateRegistry {
  def save(template: Template): Future[Template]
  def delete(id: String): Future[Unit]
}