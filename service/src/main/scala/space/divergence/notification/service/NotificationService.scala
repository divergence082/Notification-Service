package space.divergence.notification.service

import space.divergence.notification.TemplateProperties

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class NotificationService(templateService: TemplateService,
                          subscribersService: SubscriberService,
                          emailService: ChannelService,
                          pushService: ChannelService,
                          smsService: ChannelService,
                          schedulerService: SchedulerService) {
  def registerTemplate(template: String): Future[String] =
    templateService.registerTemplate(template)

  def unregisterTemplate(id: String): Future[Unit] =
    templateService.unregisterTemplate(id)

  def sendEmails(templateId: String,
                 templateProperties: TemplateProperties,
                 subscribersQuery: String,
                 limit: Option[Int] = None): Future[Unit] =
    subscribersService.select(subscribersQuery, limit).flatMap(subscribers =>
      Future.successful(subscribers.map(subscriber =>
        templateService.renderTemplate(templateId, templateProperties.withSubscriber(subscriber))
          .flatMap(message => emailService.send(message, subscriber.email))
      ))
    )
}