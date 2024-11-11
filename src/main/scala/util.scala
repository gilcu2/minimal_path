import scala.collection.AbstractIterator
import scala.io.Source
import scala.util.*

def createLinesFromFile(filePath:String):Iterator[String] =
  val source = Source.fromFile(filePath)
  source.getLines()

trait IteratorUntil[+A] extends Iterator[A] { self =>
  def takeUntil(p: A => Boolean): Iterator[A] = new AbstractIterator[A] {
    private[this] var hd: A = _
    private[this] var hdDefined: Boolean = false
    private[this] var found: Boolean = false
    private[this] var tail: Iterator[A] = self

    def hasNext = hdDefined || tail.hasNext && {
      hd = tail.next()
      if (!found) hdDefined = true
      else tail = Iterator.empty
      found=!p(hd)
      hdDefined
    }

    def next() = if (hasNext) {
      hdDefined = false; hd
    } else Iterator.empty.next()
  }
}
  
def toInt(strings:Iterator[String]): Iterator[Try[Int]]=
  strings.map(s=>Try(s.toInt)).takeWhile(_.isSuccess)