package space.divergence.notification


import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class NotificationService[Address, Text](templateRegistry: TemplateRegistry[Text],
                                         transport: Transport[Address, Text]) {
  def emit[Data](address: Address, templateId: String, data: Data): Future[Unit] = {
    templateRegistry.load(templateId)
      .flatMap(template => transport.send(address, template.render[Data](data)))
  }
}
