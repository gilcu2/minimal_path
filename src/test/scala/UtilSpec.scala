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
  
}
