package space.divergence.notification

import java.util.concurrent.ConcurrentHashMap

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try


trait TemplateRegistry[Text] {

  type Id = String

  def register(id: Id, text: Text): Future[Id]

  def load(id: Id): Future[Template[Text]]
}

object TemplateRegistry {

  type Txt = String

  class Base extends TemplateRegistry[Txt] {

    private val storage = new ConcurrentHashMap[Id, Txt]()

    override def register(id: Id, text: Txt): Future[Id] = {
      Future.fromTry(Try(storage.put(id, text)))
        .map(_ => id)
    }

    override def load(id: Id): Future[Template[Txt]] = {
      Future.fromTry(Try(storage.get(id))).map(Template.base)
    }
  }

  def base: TemplateRegistry[Txt] = new Base()
}