package butatopanto.request;

import butatopanto.test.*
import org.junit.*

class BeanRequesterTest extends GrailsJUnit4TestCase {

  @Test
  void findDirectPropertyFromBean() {
    def bean = [value: "123"]
    assertEquals(new BeanRequester(bean).findProperty("value"), "123")
  }

  @Test
  void findNestedPropertyFromBean() {
    def bean = [nested: [value: "12"]]
    assertEquals(new BeanRequester(bean).findProperty("nested.value"), "12")
  }
}