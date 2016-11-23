package space.divergence.notification

import scala.concurrent.Future


class NotificationService(templateRegistry: TemplateRegistry, transport: Transport) {
  def notify(address: Address, templateId: String, data: Data): Future[Unit] =
    templateRegistry.load(templateId)
      .flatMap(t => transport.send(address, t.render(data)))
}
