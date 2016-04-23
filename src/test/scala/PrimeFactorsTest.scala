import org.scalatest._
import org.scalatest.matchers._

import scala.collection.immutable.Stream.Empty

class PrimeFactorsTest extends FlatSpec with Matchers {
  "PrimeFactors.generate" should "return a list of integers" in {
    PrimeFactors.generate(1) shouldBe a [List[_]]
  }

  it should "return [] for 1" in {
    PrimeFactors.generate(1) shouldBe empty
  }

  it should "return [2] for 2" in {
    PrimeFactors.generate(2) should contain (2)
  }

  it should "return [3] for 3" in {
    PrimeFactors.generate(3) should contain (3)
  }

  it should "return [2,2] for 4" in {
    val result = PrimeFactors.generate(4)

    result should have length 2
    result should contain only (2)
  }

  it should "return [2,3] for 6" in {
    val result = PrimeFactors.generate(6)

    result should have length 2
    result should contain only (2,3)
  }

  it should "return [3,3] for 9" in {
    val result = PrimeFactors.generate(9)

    result should have length 2
    result should contain only (3)
  }

//  it should "not explode wiht a large prime" in {
//    PrimeFactors.generate((math.pow(2, 32)) toInt)
//  }
}
