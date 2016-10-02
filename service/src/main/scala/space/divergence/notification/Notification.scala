package space.divergence.notification

import java.time.ZonedDateTime


case class Notification(id: String,
                        subscriber: Subscriber,
                        message: String,
                        time: Option[ZonedDateTime])
