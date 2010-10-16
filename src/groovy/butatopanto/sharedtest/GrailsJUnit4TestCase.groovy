package butatopanto.sharedtest

import grails.test.GrailsUnitTestCase
import org.junit.After
import org.junit.Before
import org.springframework.validation.*

abstract class GrailsJUnit4TestCase extends GrailsUnitTestCase {

  @Before
  public void setUp() {
    super.setUp()
  }

  @After
  public void tearDown() {
    super.tearDown()
  }
}
