package space.divergence.notification

import scala.concurrent.Future


class NotificationService[Address, Data, Message](templateRegistry: TemplateRegistry[Data, Message],
                                                  transport: Transport[Address, Message]) {
  def notify(address: Address, templateId: String, data: Data): Future[Unit] =
    templateRegistry.load(templateId)
      .flatMap(template => transport.send(address, template.render(data)))
}
