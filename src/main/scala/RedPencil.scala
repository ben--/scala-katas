import com.github.nscala_time.time.Imports._

case class RedPencil(priceHistory: List[(Double, DateTime)]) {
  val Precision = 1e-9

  def redPencil: Boolean = {
    if (priceHistory.length < 2)
      false
    else if (priceHistory(0)._2 < DateTime.now - 30.days)
      false
    else {
      val discount = 1 - (priceHistory(0)._1 / priceHistory(1)._1)
      0.05 - Precision < discount && discount < 0.30 + Precision
    }
  }
}
