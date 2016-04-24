import com.github.nscala_time.time.Imports._
import org.scalatest._

class RedPencilTest extends FlatSpec with Matchers {
  "RedPencil" should "be constructed with a price history" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(25.00, time - 60.days)
    )

    RedPencil(priceHistory)
  }

  it should "not red pencil an item with no price changes" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (false)
  }

  it should "red pencil an item with a 20% drop today" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(20.00, time - 1.days),
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (true)
  }

  it should "red pencil an item with a 5% drop today" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(23.75, time - 1.days),
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (true)
  }

  it should "not red pencil an item with a 4% drop" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(23.76, time - 1.days),
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (false)
  }

  it should "red pencil an item with a 30% drop today" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(17.50, time - 1.days),
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (true)
  }

  it should "not red pencil an item with a 31% drop" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(17.49, time - 1.days),
      PriceEntry(25.00, time - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (false)
  }

  it should "red pencil for 30 days" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(20.00, time - 30.days),
      PriceEntry(25.00, time - 90.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (true)
  }

  it should "not red pencil longer than 30 days" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(20.00, time - 31.days),
      PriceEntry(25.00, time - 90.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (false)
  }

  it should "not extend the red pencil beyond 30 days for a second reduction" in {
    val time = DateTime.now
    val priceHistory = List(
      PriceEntry(19.00, time - 16.days),
      PriceEntry(20.00, time - 31.days),
      PriceEntry(25.00, time - 90.days)
    )

    println("Running failing test")
    val subject = RedPencil(priceHistory)

    subject.redPencil(time) should be (false)
  }
}
