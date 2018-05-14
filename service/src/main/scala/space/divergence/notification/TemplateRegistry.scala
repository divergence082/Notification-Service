package space.divergence.notification

import scala.concurrent.Future


trait TemplateRegistry[Data, Message] {
  def load(id: String): Future[Template[Data, Message]]
}