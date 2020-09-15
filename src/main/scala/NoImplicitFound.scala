import cats._
import cats.data.Reader
import cats.implicits._

class NoImplicitFound {
  case class Db(usernames: Map[Int, String], passwords: Map[String, String])
  type DbReader[A] = Reader[Db, A]
  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader { db =>
      db.usernames.get(userId)
    }
  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader { db =>
      db.passwords.get(username) match {
        case Some(actualPassword) => password == actualPassword
        case None => false
      }
    }
  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username   <- findUsername(userId)
      passwordOk <- username.map(username =>
        checkPassword(username, password)).getOrElse(false.pure[DbReader])
    } yield passwordOk
}


