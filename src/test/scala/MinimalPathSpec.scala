import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import difflicious._
import difflicious.scalatest.ScalatestDiff._
import scala.util._


class MinimalPathSpec extends AnyFunSpec with GivenWhenThen {

  describe("findMinimalPath") {
    it("must create minimal path of 2 levels") {
      Given("weights")
      val weights = List(
        Array(7),
        Array(6, 3),
      ).map(a=>Success(Row(a))).iterator
      val graph = TriangleGraph(weights)

      And("The expected minimal path")
      val expected = MinimalPathNode(10, List(7, 3))

      When("create")
      val minimalPath = findMinimalPath(graph).get

      Then("must be expected")
      minimalPath mustBe expected
    }

    it("must create minimal path of 3 levels") {
      Given("weights")
      val weights = List(
        Array(7),
        Array(6, 3),
        Array(3, 8, 5),
      ).map(a=>Success(Row(a))).iterator
      val graph = TriangleGraph(weights)

      And("The expected minimal path")
      val expected = MinimalPathNode(15, List(7, 3, 5))

      When("create")
      val minimalPath = findMinimalPath(graph).get

      Then("must be expected")
      minimalPath mustBe expected
    }

    it("must create minimal path of 4 levels") {
      Given("weights")
      val weights = List(
        Array(7),
        Array(6, 3),
        Array(3, 8, 5),
        Array(11, 2, 10, 9),
      ).map(a=>Success(Row(a))).iterator
      val graph = TriangleGraph(weights)

      And("The expected minimal path")
      val expected = MinimalPathNode(18, List(7, 6, 3, 2))


      When("create")
      val minimalPath = findMinimalPath(graph).get

      Then("must be expected")
      minimalPath mustBe expected
    }
  }  
}
