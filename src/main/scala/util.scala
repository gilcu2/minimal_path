import scala.collection.AbstractIterator
import scala.io.Source
import scala.util.*

def createLinesFromFile(filePath:String):Iterator[String] =
  val source = Source.fromFile(filePath)
  source.getLines()
