import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*

class MainSpec extends AnyFunSpec with GivenWhenThen {
  it("compute the minimal path from text lines") {
    Given("graph")
    val lines =
      """
        |7
        |6 3
        |3 8 5
        |11 2 10 9
        |""".stripMargin.split("\n").filter(_.nonEmpty).iterator

    And("expected result")
    val expected = "7 + 6 + 3 + 2 = 18"

    When("compute")
    val r = computeMinimalPath(lines)

    Then("it is the expected")
    r mustBe expected
  }
}
