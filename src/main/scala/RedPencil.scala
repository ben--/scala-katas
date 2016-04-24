import com.github.nscala_time.time.Imports._

case class PriceEntry(price: Double, time: DateTime)

case class RedPencil(priceHistory: List[PriceEntry]) {

  def redPencil(asOf: DateTime): Boolean = {
    if (priceHistory.length < 2)
      false
    else if (new RedPencil(priceHistory.tail).redPencil(priceHistory.head.time))
      false
    else if (priceHistory.head.time < asOf - 30.days)
      false
    else
      discountTriggersRedPencil
  }

  val Precision = 1e-9
  private val minDiscount: Double = 0.05 - Precision
  private val maxDiscount: Double = 0.30 + Precision

  private def discountTriggersRedPencil = minDiscount < discount && discount < maxDiscount

  private def discount = 1 - (priceHistory.head.price / priceHistory.tail.head.price)
}
