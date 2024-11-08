import scala.math.sqrt

case class Node(
                 weight: Int,
                 level: Int,
                 father: Option[Int] = None,
                 mother: Option[Int] = None,
                 minimalParent: Option[Int] = None,
                 distance: Int = 0
               )

case class TriangleGraph(nodes: Array[Node])

case class UpdatedGraph(nodes: Array[Node])

def mat2Linear(row: Int, col: Int): Int =
  row * (row + 1) / 2 + col

def createGraph(weights: List[Int]): TriangleGraph = {

  val nElements = weights.length
  val nRows = ((sqrt(1 + 8 * nElements) - 1) / 2).toInt
  var weightsIter = weights
  val nodes = (0 until nRows).flatMap(row => {
    (0 to row).map(column => {
      val weight = weightsIter.head
      weightsIter = weightsIter.tail
      (row, column) match
        case (0, _) => Node(weight, row)
        case (_, 0) =>
          val fatherIndex = mat2Linear(row - 1, column)
          Node(weight, row, father = Some(fatherIndex))
        case _ if column == row =>
          val fatherIndex = mat2Linear(row - 1, column - 1)
          Node(weight, row, father = Some(fatherIndex))
        case _ =>
          val fatherIndex = mat2Linear(row - 1, column - 1)
          val motherIndex = mat2Linear(row - 1, column)
          Node(weight, row, father = Some(fatherIndex), mother = Some(motherIndex))

    })
  }).toArray
  TriangleGraph(nodes)
}


def updateGraph(graph: TriangleGraph): UpdatedGraph = {

  def updateNode(node: Node): Node = {
    node
  }

  UpdatedGraph(graph.nodes.map(updateNode))
}

def findMinimalPath(graph: UpdatedGraph): Int = {

  0

}
