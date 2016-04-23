import scala.collection.immutable.Nil
import scala.collection.mutable.ListBuffer

object PrimeFactors {
  private def _generate(i:Int, n:Int) :List[Int] = (n, n % i) match {
    case (1, _) => Nil
    case (_, 0) => i :: _generate(i    , n / i)
    case (_, _) =>      _generate(i + 1, n)
  }
  def generate = _generate(2, _:Int)
}
