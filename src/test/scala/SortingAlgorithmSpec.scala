import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class SortingAlgorithmSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks{
  //A simple test to check that everything works as expected.
  "Any number" should "be equal to one (huh?)" in {
    forAll(Gen.choose(1, 100) -> "n") { n =>
      assert(n == 1, s"$n is not equal to 1")
    }
  }
}
