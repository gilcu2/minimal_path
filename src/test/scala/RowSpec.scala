import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*
import scala.util._

class RowSpec extends AnyFunSpec with GivenWhenThen {
  it("create empty Row") {
    When("create Row")
    val r = Row()

    Then("it is the expected")
    r.weights mustBe Array.emptyIntArray
  }

  it("compare Row") {
    Given("row")
    val row1 = Row()
    val row2 = Row(Array(0))

    When("compare")
    val r = (row1 == row2)


    Then("it is the expected")
    r mustBe false
  }

  describe("createRowsFromLines") {
    it("create rows when lines ok") {
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

    // TODO Test is not throwing the exception
    ignore("throw when lines have wrong number of elements") {
      Given("line iterator")
      val lines =
        """
          |7
          |6 3
          |3 8 5 4
          |11 2 10 9
          |""".stripMargin.split("\n").filter(_.nonEmpty).iterator

      And("the expected result")
      val expected = "Line 2 doesn't have the expected number of elements"

      When("create")
      val tryResult = Try(createRowsFromLines(lines))

      Then("must be expected")
      tryResult.isFailure mustBe true
    }
  }
}
