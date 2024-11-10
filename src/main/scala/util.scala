import scala.io.Source

def createLinesFromFile(filePath:String):Iterator[String] =
  val source = Source.fromFile(filePath)
  source.getLines()