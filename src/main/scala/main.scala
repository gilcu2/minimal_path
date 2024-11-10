import scala.io.Source

def computeMinimalPath(lines:Iterator[String]):String=
  val rows = createRowsFromLines(lines)
  val graph = TriangleGraph(rows)
  val minimal_path = findMinimalPath(graph)
  s"""${minimal_path.weightsPath.mkString(" + ")} = ${minimal_path.weight}"""

@main def hello(): Unit =
  val lines=Source.stdin.getLines()  
  println(computeMinimalPath(lines))

