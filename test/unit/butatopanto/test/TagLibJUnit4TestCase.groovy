package butatopanto.test;


import grails.test.TagLibUnitTestCase
import org.junit.After
import org.junit.Before

class TagLibJUnit4TestCase extends TagLibUnitTestCase {

  TagLibJUnit4TestCase() {
    super("TagLib")
  }

  TagLibJUnit4TestCase(Class tagLibClass) {
    super(tagLibClass)
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