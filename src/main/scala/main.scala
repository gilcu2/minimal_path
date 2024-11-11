import scala.io.Source
import scala.util._

def computeMinimalPath(lines:Iterator[String]):String=
  val rows = createRowsFromLines(lines)
  val graph = TriangleGraph(rows)
  val minimalPathTry = findMinimalPath(graph)
  minimalPathTry match
    case Success(minimalPath) =>
      s"""${minimalPath.weightsPath.mkString(" + ")} = ${minimalPath.weight}"""
    case Failure(exception) =>
      exception.toString

@main def hello(): Unit =
  val lines=Source.stdin.getLines()  
  println(computeMinimalPath(lines))

