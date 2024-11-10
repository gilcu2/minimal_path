import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import difflicious._
import difflicious.scalatest.ScalatestDiff._


class MinimalPathSpec extends AnyFunSpec with GivenWhenThen {

  describe("findMinimalPath") {
    it("must create minimal path of 2 levels") {
      Given("weights")
      val weights = List(
        Array(7),
        Array(6, 3),
      ).map(Row.apply)
      val graph = TriangleGraph(weights.iterator)

      And("The expected minimal path")
      val expected = 10

      When("create")
      val minimalPath = findMinimalPath(graph)

      Then("must be expected")
      minimalPath mustBe expected
    }

    it("must create minimal path of 3 levels") {
      Given("weights")
      val weights = List(
        Array(7),
        Array(6, 3),
        Array(3, 8, 5),
      ).map(Row.apply)
      val graph = TriangleGraph(weights.iterator)

      And("The expected minimal path")
      val expected = 15

      When("create")
      val minimalPath = findMinimalPath(graph)

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
      ).map(Row.apply)
      val graph = TriangleGraph(weights.iterator)

      And("The expected minimal path")
      val expected = 18

      When("create")
      val minimalPath = findMinimalPath(graph)

      Then("must be expected")
      minimalPath mustBe expected
    }
  }

  describe("createRowsFromLines") {
    it("create rows") {
      Given("line iterator")
      val lines =
        """
          |7
          |6 3
          |3 8 5
          |11 2 10 9
          |""".stripMargin.split("\n").filter(_.nonEmpty).iterator


      And("the expected result")
      val expected = List(
        Row(Array(7)),
        Row(Array(6, 3)),
        Row(Array(3, 8, 5)),
        Row(Array(11, 2, 10, 9)),
      )

      When("create")
      val row_iterator = createRowsFromLines(lines)

      Then("must be expected")
      row_iterator.toList mustBe expected
    }
  }
}
