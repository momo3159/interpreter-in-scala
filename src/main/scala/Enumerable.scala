import scala.collection.mutable.Buffer

trait Enumerable[A] {
  def foreach[B](fun: A => B): Unit

  final def map[B](f: A => B): List[B] = {
    var members = Buffer.empty[B]
    foreach {m =>
      members += f(m)
    }
    members.toList
  }

  final def toList: List[A] = {
    val members = Buffer.empty[A]
    foreach {m =>
      members += m
    }

    members.toList
  }
}
