import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers.*

class UtilSpec extends AnyFunSpec with GivenWhenThen {
  it("must read file lines") {
    Given("filepath")
    val path="data/data4.txt"
    
    When("get iterator")
    val lines=createLinesFromFile(path)
    
    Then("it is expected")
    lines.length mustBe 4
  }  
  
  it("transform to Int until error") {
    Given("string iterator")
    val strings=List("1","2","q","4").iterator
    
    When("transform")
    val tryInts=toInt(strings).toList
    
    Then("is expected")
    tryInts.length mustBe 3
    
  }
}
