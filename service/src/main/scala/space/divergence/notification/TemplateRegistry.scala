package space.divergence.notification

import scala.concurrent.Future


trait TemplateRegistry {
  def load(id: String): Future[Template]
}