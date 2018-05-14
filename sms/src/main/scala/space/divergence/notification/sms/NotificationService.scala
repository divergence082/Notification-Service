package space.divergence.notification.sms

import space.divergence.notification.{NotificationService, TemplateRegistry}

object NotificationService {

  def apply[Data](url: String,
                  apiKey: String,
                  templateRegistry: TemplateRegistry[Data, Message]) =
    new NotificationService[Address, Data, Message](templateRegistry, new Transport(url, apiKey))
}
