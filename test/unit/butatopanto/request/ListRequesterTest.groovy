package butatopanto.request;

import butatopanto.test.*
import org.junit.*
import static butatopanto.request.ListRequester.*;

class ListRequesterTest extends GrailsJUnit4TestCase {

  @Test
  void getsAllElementsForLargeLimit() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).getAtMostElements(10)
    assertEquals([[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]], requestedElements)
  }

  @Test
  void restrainsResultAccordingToLimit() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).getAtMostElements(2)
    assertEquals([[value: "1"], [value: "1234"]], requestedElements)
  }

  @Test
  void restrainsResultAccordingToOffset() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).startingFromIndex(4).getAtMostElements(10)
    assertEquals([[value: "123"], [value: "123456"]], requestedElements)
  }

  @Test
  void respectsAscendingSortingByProperty() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).sortedAscendingBy("value").getAtMostElements(10)
    assertEquals([[value: "1"], [value: "12"], [value: "123"], [value: "1234"], [value: "12345"], [value: "123456"]], requestedElements)
  }

  @Test
  void respectsDescendingSortingByProperty() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).sortedDescendingBy("value").getAtMostElements(10)
    assertEquals([[value: "123456"], [value: "12345"], [value: "1234"], [value: "123"], [value: "12"], [value: "1"]], requestedElements)
  }

  @Test
  void sortsPriorToRestriction() {
    def all = [[value: "1"], [value: "1234"], [value: "12"], [value: "12345"], [value: "123"], [value: "123456"]]
    def requestedElements = From(all).sortedAscendingBy("value").getAtMostElements(3)
    assertEquals([[value: "1"], [value: "12"], [value: "123"]], requestedElements)
  }
}