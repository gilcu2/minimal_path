import scala.util._

case class Row(weights: Array[Int] = Array.emptyIntArray) {
  override def equals(that: Any): Boolean =
    that match {
      case that: Row => that.weights.toList == weights.toList
      case _ => false
    }
}

def splitLine(line: String, lineIndex: Int): Try[(Array[String], Int)] =
  val divided = line.split(" ")
  if divided.length == lineIndex + 1 then
    Success((divided, lineIndex))
  else    
    Failure(Exception(s"Line $lineIndex number of elements ${divided.length} different than expected ${lineIndex + 1}"))

def toRow(divided: Array[String], lineIndex: Int): Row =
  val tryValues = Try(divided.map(_.toInt))
  tryValues match
    case Success(ints) => Row(ints)
    case Failure(exception) => 
      throw Exception(s"Problem reading line $lineIndex: $exception")


def createRowsFromLines(lines: Iterator[String]): Iterator[Row] =
  lines
    .zipWithIndex
    .map(splitLine)
    .map {
      case Success((divided, lineIndex)) =>
        toRow(divided, lineIndex)
      case Failure(exception) =>
        throw exception
    }


case class MinimalPathNode(weight: Int, weightsPath: List[Int])

case class MinimalPathRow(nodes: Array[MinimalPathNode] = Array.empty[MinimalPathNode])

case class TriangleGraph(rows: Iterator[Row])

def findMinimalPath(graph: TriangleGraph): Try[MinimalPathNode] = {
  val tryResult = Try(graph.rows.foldLeft((0, MinimalPathRow())) {
    case ((level, previousRow), weights) =>
      (level + 1, computeNextRow(level, previousRow, weights))
  })
  tryResult.map(result => {
    val minNode = result._2
      .nodes
      .minBy(_.weight)
    minNode.copy(weightsPath = minNode.weightsPath.reverse)
  })
}

def computeNextRow(level: Int, previousRow: MinimalPathRow, graphRow: Row): MinimalPathRow = {
  def makeNode(previousNode: Int, column: Int) =
    MinimalPathNode(
      previousRow.nodes(previousNode).weight + graphRow.weights(column),
      graphRow.weights(column) :: previousRow.nodes(previousNode).weightsPath
    )

  val nextRow = for column <- 0 to level
    yield (level, column) match {
      case (0, _) =>
        MinimalPathNode(graphRow.weights(0), List(graphRow.weights(0)))
      case (_, 0) =>
        makeNode(0, 0)
      case _ if column == level =>
        makeNode(column - 1, column)
      case _ =>
        val previousNode = if previousRow.nodes(column - 1).weight <= previousRow.nodes(column).weight then
          column - 1
        else
          column
        makeNode(previousNode, column)
    }
  MinimalPathRow(nextRow.toArray)
}


