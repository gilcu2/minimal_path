import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import difflicious._
import difflicious.scalatest.ScalatestDiff._


class MinimalPathSpec extends AnyFunSpec with GivenWhenThen {

  it("must create minimal path of 2 levels") {
    Given("weights")
    val weights = Array(
      Array(7),
      Array(6, 3),
    )
    val graph = TriangleGraph(LazyList.tabulate(weights.length)(level => Row(weights(level))))

    And("The expected minimal path")
    val expected = 10

    When("create")
    val minimalPath = findMinimalPath(graph)

    Then("must be expected")
    minimalPath mustBe expected
    }

  it("must create minimal path of 3 levels") {
    Given("weights")
    val weights = Array(
      Array(7),
      Array(6, 3),
      Array(3, 8, 5),
    )
    val graph = TriangleGraph(LazyList.tabulate(weights.length)(level => Row(weights(level))))

    And("The expected minimal path")
    val expected = 15

    When("create")
    val minimalPath = findMinimalPath(graph)

    Then("must be expected")
    minimalPath mustBe expected
  }

  it("must create minimal path of 4 levels") {
    Given("weights")
    val weights = Array(
      Array(7),
      Array(6, 3),
      Array(3, 8, 5),
      Array(11, 2, 10, 9),
    )
    val graph = TriangleGraph(LazyList.tabulate(weights.length)(level => Row(weights(level))))

    And("The expected minimal path")
    val expected = 18

    When("create")
    val minimalPath = findMinimalPath(graph)

    Then("must be expected")
    minimalPath mustBe expected
  }


}
