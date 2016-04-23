import com.github.nscala_time.time.Imports._
import org.scalatest._

class RedPencilTest extends FlatSpec with Matchers {
  "RedPencil" should "be constructed with a price history" in {
    val priceHistory = List(
      (25.00, DateTime.now - 60.days)
    )

    RedPencil(priceHistory)
  }

  it should "not red pencil an item with no price changes" in {
    val priceHistory = List(
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (false)
  }

  it should "red pencil an item with a 20% drop today" in {
    val priceHistory = List(
      (20.00, DateTime.now - 1.days),
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (true)
  }

  it should "red pencil an item with a 5% drop today" in {
    val priceHistory = List(
      (23.75, DateTime.now - 1.days),
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (true)
  }

  it should "not red pencil an item with a 4% drop" in {
    val priceHistory = List(
      (23.76, DateTime.now - 1.days),
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (false)
  }

  it should "red pencil an item with a 30% drop today" in {
    val priceHistory = List(
      (17.50, DateTime.now - 1.days),
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (true)
  }

  it should "not red pencil an item with a 31% drop" in {
    val priceHistory = List(
      (17.49, DateTime.now - 1.days),
      (25.00, DateTime.now - 60.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (false)
  }

  it should "not red pencil longer than 30 days" in {
    val priceHistory = List(
      (20.00, DateTime.now - 31.days),
      (25.00, DateTime.now - 90.days)
    )

    val subject = RedPencil(priceHistory)

    subject.redPencil should be (false)
  }
}
