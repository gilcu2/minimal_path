import scala.io.Source

@main def hello(): Unit =
  val lines=Source.stdin.getLines()
  val rows=createRowsFromLines(lines)
  val graph=TriangleGraph(rows)
  val minimal_path=findMinimalPath(graph)
  println(minimal_path)

