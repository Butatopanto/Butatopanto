package butatopanto.sharedtest

import grails.test.ControllerUnitTestCase
import org.junit.After
import org.junit.Before

abstract class GrailsJUnit4ControllerTestCase extends ControllerUnitTestCase {

  GrailsJUnit4ControllerTestCase(Class controllerClass) {
    super(controllerClass)
  }

  @Before
  public void setUp() {
    super.setUp()
  }

  @After
  public void tearDown() {
    super.tearDown()
  }
}
