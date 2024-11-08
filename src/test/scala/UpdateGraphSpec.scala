import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers._

class UpdateGraphSpec extends AnyFunSpec with GivenWhenThen {

  it("must create graph of 2 levels") {
    Given("graph")
    val graph = TriangleGraph(Array(
      Node(7, 0),
      Node(6, 1, Some(0)),
      Node(3, 1, Some(0)),
    ))

    And("The expected updated graph")
    val expected = UpdatedGraph(Array(
      Node(7, 0, distance = 7),
      Node(6, 1, Some(0), minimalParent = Some(0), distance = 13),
      Node(3, 1, Some(0), minimalParent = Some(0), distance = 10),
    ))

    When("update")
    val updatedGraph = updateGraph(graph)

    Then("must be expected")
    graph mustBe expected
  }

}
