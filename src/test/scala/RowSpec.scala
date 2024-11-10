import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*

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

}
