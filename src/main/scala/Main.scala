object Main {
  def main(args: Array[String]): Unit = {
    val results = (1 to 3).flatMap { i =>
      (2 to 4).flatMap { j =>
        (3 to 5).map { k => i * j * k }.filter(_ % 3 == 0)
      }
    }
    println(results)

    val results_ = for {
      i <- (1 to 3)
      j <- (2 to 4)
      k <- (3 to 5)
      result = (i * j * k) if result % 3 == 0
    } yield result
  }
}
