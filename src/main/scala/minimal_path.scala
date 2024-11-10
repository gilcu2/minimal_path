import scala.math.min

case class Row(weights: Array[Int] = Array.emptyIntArray) {
  override def equals(that: Any): Boolean =
    that match {
      case that: Row => that.weights.toList == weights.toList
      case _ => false
    }
}

case class TriangleGraph(rows: Iterator[Row])

def computeNextRow(level: Int, previousRow: Row, graphRow: Row): Row = {
  val weightsNextRow = for column <- 0 to level
    yield (level, column) match {
      case (0, _) => graphRow.weights(0)
      case (_, 0) => previousRow.weights(0) + graphRow.weights(0)
      case _ if column == level =>
        previousRow.weights(column - 1) + graphRow.weights(column)
      case _ =>
        min(previousRow.weights(column - 1), previousRow.weights(column)) + graphRow.weights(column)
    }
  Row(weightsNextRow.toArray)
}

def findMinimalPath(graph: TriangleGraph): Int =
  val (_, lastRow) = graph.rows.foldLeft((0, Row())) { case ((level, previousRow), weights) =>
    (level + 1, computeNextRow(level, previousRow, weights))
  }
  lastRow.weights.min

def createRowsFromLines(lines:Iterator[String]): Iterator[Row] =
  lines.map(_.split(" ")).map(a=>Row(a.map(x=>x.toInt)))



