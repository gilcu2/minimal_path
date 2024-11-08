import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import difflicious._
import difflicious.scalatest.ScalatestDiff._


class CreateGraphSpec extends AnyFunSpec with GivenWhenThen {

  describe("mat2Linear") {
    it("compute linear index from row and col") {
      Given("matrix and linear indexes")
      val testTable = Table(
        ("row", "column", "index"),
        (0, 0, 0),
        (1, 0, 1),
        (1, 1, 2),
        (2, 0, 3),
        (2, 1, 4),
        (2, 2, 5),
      )

      When("compute the linear it is the expected")
      forAll(testTable) { (row, column, index) =>
        mat2Linear(row, column) mustBe index
      }
    }
  }

  describe("graph creation") {

    implicit val differ: Differ[Node] = Differ.derived

    it("must create graph of 2 levels") {
      Given("weights")
      val weights = List(7, 6, 3)

      And("The expected graph")
      val expected = TriangleGraph(Array(
        Node(7, 0),
        Node(6, 1, Some(0)),
        Node(3, 1, Some(0)),
      ))

      When("create")
      val graph = createGraph(weights)

      Then("must be expected")
      Differ[List[Node]].assertNoDiff(graph.nodes.toList, expected.nodes.toList)
    }

    it("must create graph of 3 levels") {
      Given("weights")
      val weights = List(7, 6, 3, 3, 8, 5)

      And("The expected graph")
      val expected = TriangleGraph(Array(
        Node(7, 0),
        Node(6, 1, Some(0)),
        Node(3, 1, Some(0)),
        Node(3, 2, Some(1)),
        Node(8, 2, Some(1), Some(2)),
        Node(5, 2, Some(2)),
      ))

      When("create")
      val graph = createGraph(weights)

      Then("must be expected")
      Differ[List[Node]].assertNoDiff(graph.nodes.toList, expected.nodes.toList)
    }

    it("must create graph of 4 levels") {
      Given("weights")
      val weights = List(7, 6, 3, 3, 8, 5, 11, 2, 10, 9)

      And("The expected graph")
      val expected = TriangleGraph(Array(
        Node(7, 0),
        Node(6, 1, Some(0)),
        Node(3, 1, Some(0)),
        Node(3, 2, Some(1)),
        Node(8, 2, Some(1), Some(2)),
        Node(5, 2, Some(2)),
        Node(11, 3, Some(3)),
        Node(2, 3, Some(3), Some(4)),
        Node(10, 3, Some(4), Some(5)),
        Node(9, 3, Some(5)),
      ))

      When("create")
      val graph = createGraph(weights)

      Then("must be expected")
      Differ[List[Node]].assertNoDiff(graph.nodes.toList, expected.nodes.toList)
    }

  }

}
